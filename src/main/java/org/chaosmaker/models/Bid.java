package org.chaosmaker.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

@Entity
@Table(
        name = "BID"
)
@NamedEntityGraphs({
        @NamedEntityGraph(
                name = "BidBidderItemSellerBids",
                attributeNodes = {
                        @NamedAttributeNode(value = "bidder"),
                        @NamedAttributeNode(
                                value = "item",
                                subgraph = "ItemSellerBids"
                        )
                },
                subgraphs = {
                        @NamedSubgraph(
                                name = "ItemSellerBids",
                                attributeNodes = {
                                        @NamedAttributeNode("seller"),
                                        @NamedAttributeNode("bids")
                                })
                }
        )
})
public class Bid {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "custom_bid_generator")
    @SequenceGenerator(
            name = "custom_bid_generator",
            sequenceName = "GENERATOR_SEQUENCE",
            initialValue = 15
    )
    protected Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    protected Item item;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    protected User bidder;

    @NotNull
    protected BigDecimal amount;

    public Bid() {
    }

    public Bid(BigDecimal amount) {
        this.amount = amount;
    }

    public Bid(Item item, User bidder, BigDecimal amount) {
        this.item = item;
        this.amount = amount;
        this.bidder = bidder;
    }

    public Long getId() {
        return id;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public User getBidder() {
        return bidder;
    }

    public void setBidder(User bidder) {
        this.bidder = bidder;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
