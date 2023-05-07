package exercise.controllers;

import io.javalin.http.Context;
import io.javalin.apibuilder.CrudHandler;
import io.ebean.DB;
import java.util.List;

import exercise.domain.User;
import exercise.domain.query.QUser;

import org.apache.commons.validator.routines.EmailValidator;
import org.apache.commons.lang3.StringUtils;

public class UserController implements CrudHandler {

    public void getAll(Context ctx) {
        // BEGIN
        List<User> users = new QUser().findList();
        String json = DB.json().toJson(users);
        ctx.result(json);
        // END
    };

    public void getOne(Context ctx, String id) {

        // BEGIN
        User user = new QUser().setId(id).findOne();
        if (user != null) {
            String json = DB.json().toJson(user);
            ctx.result(json);
        } else {
            ctx.status(404);
        }
        // END
    };

    public void create(Context ctx) {

        // BEGIN
        User user = DB.json().toBean(User.class, ctx.body());
        if (user != null) {
            user.save();
            String json = DB.json().toJson(user);
            ctx.result(json).status(201);
        } else {
            ctx.status(400);
        }
        // END
    };

    public void update(Context ctx, String id) {
        // BEGIN
        User existingUser = new QUser().setId(id).findOne();
        if (existingUser != null) {
            User updatedUser = DB.json().toBean(User.class, ctx.body());
            if (updatedUser != null) {
                existingUser.setId(id);
                existingUser.update();
                String json = DB.json().toJson(existingUser);
                ctx.result(json);
            } else {
                ctx.status(400);
            }
        } else {
            ctx.status(404);
        }
        // END
    };

    public void delete(Context ctx, String id) {
        // BEGIN
        User user = DB.find(User.class, id);
        if (user != null) {
            user.delete();
            ctx.status(204);
        } else {
            ctx.status(404);
        }
        // END
    };
}
