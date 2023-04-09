package rs.ac.uns.ftn.informatika.svtprojekat.entity.dto;

import rs.ac.uns.ftn.informatika.svtprojekat.entity.Rule;

public class RuleDTO {

    private Integer id;

    private String description;

    private String communityId;

    public RuleDTO(Integer id, String description, String communityId) {
        this.id = id;
        this.description = description;
        this.communityId = communityId;
    }

    public RuleDTO(Rule rule) {
        this(rule.getId(), rule.getDescription(), rule.getCommunityId());
    }
}
