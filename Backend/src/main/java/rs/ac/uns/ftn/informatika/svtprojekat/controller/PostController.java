package rs.ac.uns.ftn.informatika.svtprojekat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.informatika.svtprojekat.entity.*;
import rs.ac.uns.ftn.informatika.svtprojekat.entity.dto.CommentDTO;
import rs.ac.uns.ftn.informatika.svtprojekat.entity.dto.PostDTO;
import rs.ac.uns.ftn.informatika.svtprojekat.entity.dto.PostDTOandorid;
import rs.ac.uns.ftn.informatika.svtprojekat.service.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "posts")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    @Autowired
    private FlairService flairService;

    @Autowired
    private CommunityService communityService;

    @Autowired
    private ReactionService reactionService;

    @Autowired
    private CommentService commentService;


    @GetMapping
    public ResponseEntity<List<PostDTO>> getPosts() {
        List<Post> posts = postService.findAll();

        List<PostDTO> postsDTO = new ArrayList<>();
        for (Post p : posts) {
            System.out.println(p.toString());
            PostDTO post = new PostDTO(p);
            post.setKarma(reactionService.getKarma(p));
            postsDTO.add(post);
        }

        return new ResponseEntity<>(postsDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/community/{id}")
    public ResponseEntity<List<PostDTO>> getPostsFromCommunity(@PathVariable("id") Integer communityId) {
        Community community = communityService.findOne(communityId);
        List<Post> posts = postService.findAllFromCommunity(community);

        List<PostDTO> postsDTO = new ArrayList<>();
        for (Post p : posts) {
            System.out.println(p.toString());
            PostDTO post = new PostDTO(p);
            post.setKarma(reactionService.getKarma(p));
            postsDTO.add(post);
        }

        return new ResponseEntity<>(postsDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/android")
    public ResponseEntity<List<PostDTOandorid>> getPostsAndroid() {
        List<Post> posts = postService.findAll();

        List<PostDTOandorid> postsDTO = new ArrayList<>();
        for (Post p : posts) {
            System.out.println(p.toString());
            PostDTOandorid post = new PostDTOandorid(p);
            post.setKarma(reactionService.getKarma(p));
            postsDTO.add(post);
        }

        return new ResponseEntity<>(postsDTO, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('USER', 'ROLE_ADMIN')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<PostDTO> getPost(@PathVariable("id") Integer id) {
        Post post = postService.findOne(id);
        if (post == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new PostDTO(post), HttpStatus.OK);
    }

    @Transactional
    @PreAuthorize("hasAnyRole('USER', 'ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<PostDTO> postPost(@RequestBody PostDTO postDTO){
        Post post = new Post();
        LocalDate date = LocalDate.now();

        post.setFlair(flairService.findOne(postDTO.getFlair().getId()));
        post.setCommunity(communityService.findOne(postDTO.getCommunity().getId()));
        if(postDTO.getUser().getId() == null) {
            post.setUser(userService.findUserByUsername(postDTO.getUser().getUsername()));
        }
        else {
            post.setUser(userService.findOne(postDTO.getUser().getId()));
        }

        post.setCreationDate(date);
        post.setImagePath(postDTO.getImagePath());
        post.setText(postDTO.getText());
        post.setTitle(postDTO.getTitle());

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User u = (User) auth.getPrincipal();
        rs.ac.uns.ftn.informatika.svtprojekat.entity.User user = userService.findUserByUsername(u.getUsername());

        if(post.getImagePath() == null || post.getText() == null ||
            post.getTitle() == null || post.getFlair() == null ||
            post.getCommunity() == null || post.getUser() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Reaction reaction = new Reaction();
        LocalDate ts = LocalDate.now();
        reaction.setPost(post);
        reaction.setType(ReactionTypeENUM.UPVOTE);
        reaction.setUser(user);
        //reaction.setUser(post.getUser());
        reaction.setTimestamp(ts);
        reactionService.save(reaction);
        postService.save(post);
        return new ResponseEntity<>(new PostDTO(post), HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyRole('USER', 'ROLE_ADMIN')")
    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping(value = "/{id}")
    public ResponseEntity<PostDTO> putPost(@RequestBody PostDTO postDTO, @PathVariable("id") Integer id){
        if (id != null) {
            Post post = postService.findOne(id);

            if(post == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            User u = (User) auth.getPrincipal();
            rs.ac.uns.ftn.informatika.svtprojekat.entity.User user = userService.findUserByUsername(u.getUsername());

            System.out.println("user id: " + user.getId());
            System.out.println("user id from post: " + post.getUser().getId());
            if(!user.getId().equals(post.getUser().getId())) {
                return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
            }

            post.setFlair(flairService.findOne(postDTO.getFlair().getId()));
            post.setCommunity(communityService.findOne(postDTO.getCommunity().getId()));
            post.setUser(userService.findOne(postDTO.getUser().getId()));

            post.setImagePath(postDTO.getImagePath());
            post.setText(postDTO.getText());
            post.setTitle(postDTO.getTitle());

            if(post.getImagePath() == null || post.getText() == null ||
                    post.getTitle() == null || post.getFlair() == null ||
                    post.getCommunity() == null || post.getUser() == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            postService.save(post);
            return new ResponseEntity<>(new PostDTO(post), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @Transactional
    @PreAuthorize("hasAnyRole('USER', 'ROLE_ADMIN')")
    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity deletePost(@PathVariable("id") Integer id) {
        if (id != null) {
            Post post = postService.findOne(id);

            if(post == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            User u = (User) auth.getPrincipal();
            rs.ac.uns.ftn.informatika.svtprojekat.entity.User user = userService.findUserByUsername(u.getUsername());

            System.out.println("user id: " + user.getId());
            System.out.println("user id from post: " + post.getUser().getId());
            if(!user.getId().equals(post.getUser().getId())) {
                return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
            }
            commentService.deleteAllByPost(post);
            reactionService.deleteAllbyPost(post);
            postService.remove(post.getId());

            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @PreAuthorize("hasAnyRole('USER', 'ROLE_ADMIN')")
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping(value = "/{id}/upVotes")
    public HttpStatus upVote(@PathVariable("id") Integer id) {
        if (id != null) {
            Post post = postService.findOne(id);
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            User u = (User) auth.getPrincipal();
            rs.ac.uns.ftn.informatika.svtprojekat.entity.User user = userService.findUserByUsername(u.getUsername());
            Integer userId = user.getId();

            if(post == null) {
                return HttpStatus.NOT_FOUND;
            }

            if(!reactionService.checkIfReactionExists(userId, post)){
                postService.upVote(userId, post);
                return HttpStatus.OK;
            }
            else {
                reactionService.undoReaction(userId, post);
                return HttpStatus.ACCEPTED;
            }
        }
        return HttpStatus.BAD_REQUEST;
    }

    @PreAuthorize("hasAnyRole('USER', 'ROLE_ADMIN')")
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping(value = "/{id}/downVotes")
    public HttpStatus downVote(@PathVariable("id") Integer id) {
        if (id != null) {
            Post post = postService.findOne(id);
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            User u = (User) auth.getPrincipal();
            rs.ac.uns.ftn.informatika.svtprojekat.entity.User user = userService.findUserByUsername(u.getUsername());
            Integer userId = user.getId();

            if(post == null) {
                return HttpStatus.NOT_FOUND;
            }

            if(!reactionService.checkIfReactionExists(userId, post)){
                postService.downVote(userId, post);
                return HttpStatus.OK;
            }
            else {
                reactionService.undoReaction(userId, post);
                return HttpStatus.ACCEPTED;
            }

        }
        return HttpStatus.BAD_REQUEST;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(value = "/{id}/comments")
    public ResponseEntity<List<CommentDTO>> getComments(@PathVariable("id") Integer id) {
        Post post = postService.findOne(id);
        List<Comment> comments = commentService.findAllByPost(post);

        List<CommentDTO> commentsDTO = new ArrayList<>();
        for (Comment c : comments) {
            System.out.println(c.toString());
            CommentDTO comment = new CommentDTO(c);
            comment.setKarma(reactionService.getCommentKarma(c));
            commentsDTO.add(comment);
        }

        return new ResponseEntity<>(commentsDTO, HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @PostMapping(value = "/{id}/comments/{commentId}/upVotes")
    public HttpStatus upVoteComment(@PathVariable("id") Integer id, @PathVariable("commentId") Integer commentId) {
        if (id != null && commentId != null) {
            Comment comment = commentService.findOne(commentId);
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            User u = (User) auth.getPrincipal();
            rs.ac.uns.ftn.informatika.svtprojekat.entity.User user = userService.findUserByUsername(u.getUsername());
            Integer userId = user.getId();

            if(comment == null) {
                return HttpStatus.NOT_FOUND;
            }

            if(!reactionService.checkIfReactionExists(userId, comment)){
                reactionService.upVoteComment(userId, comment);
                return HttpStatus.OK;
            }
            else {
                reactionService.undoReaction(userId, comment);
                return HttpStatus.ACCEPTED;
            }
        }
        return HttpStatus.BAD_REQUEST;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @PostMapping(value = "/{id}/comments/{commentId}/downVotes")
    public HttpStatus downVoteComment(@PathVariable("id") Integer id, @PathVariable("commentId") Integer commentId) {
        if (id != null && commentId != null) {
            Comment comment = commentService.findOne(commentId);
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            User u = (User) auth.getPrincipal();
            rs.ac.uns.ftn.informatika.svtprojekat.entity.User user = userService.findUserByUsername(u.getUsername());
            Integer userId = user.getId();

            if(comment == null) {
                return HttpStatus.NOT_FOUND;
            }

            if(!reactionService.checkIfReactionExists(userId, comment)){
                reactionService.downVoteComment(userId, comment);
                return HttpStatus.OK;
            }
            else {
                reactionService.undoReaction(userId, comment);
                return HttpStatus.ACCEPTED;
            }
        }
        return HttpStatus.BAD_REQUEST;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping(value = "/{id}/comments")
    @PreAuthorize("hasAnyRole('USER', 'ROLE_ADMIN')")
    public HttpStatus postComment(@PathVariable("id") Integer id, @RequestBody CommentDTO commentDTO) {

        if(id != null){
            if(commentDTO.getText() == null){
                return HttpStatus.NOT_ACCEPTABLE;
            }
            else {
                Comment comment = new Comment();
                Post post = postService.findOne(id);

                Authentication auth = SecurityContextHolder.getContext().getAuthentication();
                User u = (User) auth.getPrincipal();
                rs.ac.uns.ftn.informatika.svtprojekat.entity.User user = userService.findUserByUsername(u.getUsername());

                comment.setPost(post);
                comment.setText(commentDTO.getText());
                comment.setUser(user);
                comment.setDeleted(false);
                comment.setTimestamp(LocalDate.now());
                commentService.save(comment);
                return HttpStatus.OK;
            }
        }
        else {
            return  HttpStatus.BAD_REQUEST;
        }

    }

    @Transactional
    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping(value = "/{id}/comments/{commentId}")
    @PreAuthorize("hasAnyRole('USER', 'ROLE_ADMIN')")
    public HttpStatus deleteComment(@PathVariable("id") Integer id, @PathVariable("commentId") Integer commentId) {

        if(id != null && commentId != null){
            Comment comment = commentService.findOne(commentId);
            if(comment == null){
                return HttpStatus.NOT_FOUND;
            }
            else {

                Post post = postService.findOne(id);

                Authentication auth = SecurityContextHolder.getContext().getAuthentication();
                User u = (User) auth.getPrincipal();
                rs.ac.uns.ftn.informatika.svtprojekat.entity.User user = userService.findUserByUsername(u.getUsername());

                if(user.getId().equals(comment.getUser().getId())) {
                    reactionService.deleteAllbyComment(comment);
                    commentService.remove(commentId);
                    return HttpStatus.OK;
                }
                else {
                    return HttpStatus.UNAUTHORIZED;
                }
            }
        }
        else {
            return  HttpStatus.BAD_REQUEST;
        }

    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping(consumes = "text/plain", value = "/{id}/comments/{commentId}")
    @PreAuthorize("hasAnyRole('USER', 'ROLE_ADMIN')")
    public HttpStatus editComment(@PathVariable("id") Integer id, @PathVariable("commentId") Integer commentId, @RequestBody String text) {

        if(id != null && commentId != null && text != null){
            Comment comment = commentService.findOne(commentId);
            if(comment == null){
                return HttpStatus.NOT_FOUND;
            }
            else {
                Authentication auth = SecurityContextHolder.getContext().getAuthentication();
                User u = (User) auth.getPrincipal();
                rs.ac.uns.ftn.informatika.svtprojekat.entity.User user = userService.findUserByUsername(u.getUsername());

                if(user.getId().equals(comment.getUser().getId())) {
                    comment.setText(text);
                    commentService.save(comment);
                    return HttpStatus.OK;
                }
                else {
                    return HttpStatus.UNAUTHORIZED;
                }
            }
        }
        else {
            return  HttpStatus.BAD_REQUEST;
        }

    }
}
