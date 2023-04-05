package rs.ac.uns.ftn.informatika.svtprojekat.entity.dto;

import rs.ac.uns.ftn.informatika.svtprojekat.entity.Community;
import rs.ac.uns.ftn.informatika.svtprojekat.entity.Rule;

public class RuleDTO {

    private Integer id;

    private String description;

    private CommunityDTO community;

    public RuleDTO(Integer id, String description, CommunityDTO community) {
        this.id = id;
        this.description = description;
        this.community = community;
    }

    public RuleDTO(Rule rule) {
        this(rule.getId(), rule.getDescription(), new CommunityDTO(rule.getCommunity()));
    }
}
