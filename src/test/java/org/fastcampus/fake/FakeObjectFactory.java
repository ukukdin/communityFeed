package org.fastcampus.fake;

import org.fastcampus.post.application.CommentService;
import org.fastcampus.post.application.Interfaces.CommentRepository;
import org.fastcampus.post.application.Interfaces.LikeRepository;
import org.fastcampus.post.application.Interfaces.PostRepository;
import org.fastcampus.post.application.PostService;
import org.fastcampus.post.repository.FakeCommentRepository;
import org.fastcampus.post.repository.FakePostRepository;
import org.fastcampus.user.application.UserRelationService;
import org.fastcampus.user.application.UserService;
import org.fastcampus.user.application.interfaces.UserRelationRepository;
import org.fastcampus.user.application.interfaces.UserRepository;
import org.fastcampus.user.repository.FakeUserRelationRepository;
import org.fastcampus.user.repository.FakeUserRepository;

public class FakeObjectFactory {

    private static final UserRepository fakeUserRepository = new FakeUserRepository();
    private static final UserRelationRepository fakeUserRelationRepository = new FakeUserRelationRepository();

    private static final PostRepository fakePostRepository = new FakePostRepository();
    private static final CommentRepository fakeCommentRepository = new FakeCommentRepository();
    private static final LikeRepository fakeLikeRepository = new FakeLikeRepository();
    private static final UserService userService = new UserService(fakeUserRepository);
    private static final UserRelationService userRelationService = new UserRelationService(fakeUserRelationRepository,userService);
    private static final PostService postService = new PostService(userService,fakePostRepository, fakeLikeRepository);
    private static final CommentService commentService = new CommentService(userService,postService, fakeCommentRepository, fakeLikeRepository);


    private FakeObjectFactory() {}
}
