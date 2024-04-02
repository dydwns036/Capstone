package com.example.foodapp.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.foodapp.model.Post;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Tên cơ sở dữ liệu và phiên bản
    private static final String DATABASE_NAME = "my_social_app.db";
    private static final int DATABASE_VERSION = 1;

    // Tên các bảng và cột trong cơ sở dữ liệu
    private static final String TABLE_USERS = "users";
    private static final String COLUMN_USER_ID = "user_id";
    private static final String COLUMN_USERNAME = "username";
    private static final String COLUMN_USERACCNAME = "useraccname";

    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_PASSWORD = "password";
    private static final String AVATAR_IMAGE = "avatar_image";
    private static final String COVER_IMAGE = "cover_image";


    private static final String COLUMN_IS_ADMIN = "is_admin";

    private static final String TABLE_POSTS = "posts";
    private static final String COLUMN_POST_ID = "post_id";
    private static final String COLUMN_POST_GROUP= "post_group";
    private static final String COLUMN_POST_CONTENT = "content";
    private static final String COLUMN_CREATED_AT = "created_at";

    private static final String TABLE_PHOTOS = "photos";
    private static final String COLUMN_PHOTO_ID = "photo_id";
    private static final String COLUMN_IMAGE_URL = "image_url";

    private static final String TABLE_LIKES = "likes";
    private static final String COLUMN_LIKE_ID = "like_id";

    private static final String TABLE_COMMENTS = "comments";
    private static final String COLUMN_COMMENT_ID = "comment_id";
    private static final String COLUMN_COMMENT_CONTENT = "comment_content";

    // Câu truy vấn tạo bảng người dùng
    private static final String CREATE_TABLE_USERS = "CREATE TABLE " + TABLE_USERS + "("
            + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_USERNAME + " TEXT,"
            + COLUMN_USERACCNAME + " TEXT,"
            + COLUMN_EMAIL + " TEXT,"
            + COLUMN_PASSWORD + " TEXT,"
            + AVATAR_IMAGE + " TEXT,"
            + COVER_IMAGE + " TEXT,"
            + COLUMN_IS_ADMIN + " INTEGER DEFAULT 0"
            + ")";

    // Câu truy vấn tạo bảng bài viết
    private static final String CREATE_TABLE_POSTS = "CREATE TABLE " + TABLE_POSTS + "("
            + COLUMN_POST_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_POST_GROUP + " INTEGER,"
            + COLUMN_USER_ID + " INTEGER,"
            + COLUMN_POST_CONTENT + " TEXT,"
            + COLUMN_CREATED_AT + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP,"
            + "FOREIGN KEY(" + COLUMN_USER_ID + ") REFERENCES " + TABLE_USERS + "(" + COLUMN_USER_ID + ")"
            + ")";

    // Câu truy vấn tạo bảng ảnh
    private static final String CREATE_TABLE_PHOTOS = "CREATE TABLE " + TABLE_PHOTOS + "("
            + COLUMN_PHOTO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_POST_ID + " INTEGER,"
            + COLUMN_IMAGE_URL + " TEXT,"
            + "FOREIGN KEY(" + COLUMN_POST_ID + ") REFERENCES " + TABLE_POSTS + "(" + COLUMN_POST_ID + ")"
            + ")";

    // Câu truy vấn tạo bảng lượt thích
    private static final String CREATE_TABLE_LIKES = "CREATE TABLE " + TABLE_LIKES + "("
            + COLUMN_LIKE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_USER_ID + " INTEGER,"
            + COLUMN_POST_ID + " INTEGER,"
            + "FOREIGN KEY(" + COLUMN_USER_ID + ") REFERENCES " + TABLE_USERS + "(" + COLUMN_USER_ID + "),"
            + "FOREIGN KEY(" + COLUMN_POST_ID + ") REFERENCES " + TABLE_POSTS + "(" + COLUMN_POST_ID + ")"
            + ")";

    // Câu truy vấn tạo bảng bình luận
    private static final String CREATE_TABLE_COMMENTS = "CREATE TABLE " + TABLE_COMMENTS + "("
            + COLUMN_COMMENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_USER_ID + " INTEGER,"
            + COLUMN_POST_ID + " INTEGER,"
            + COLUMN_COMMENT_CONTENT + " TEXT,"
            + COLUMN_CREATED_AT + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP,"
            + "FOREIGN KEY(" + COLUMN_USER_ID + ") REFERENCES " + TABLE_USERS + "(" + COLUMN_USER_ID + "),"
            + "FOREIGN KEY(" + COLUMN_POST_ID + ") REFERENCES " + TABLE_POSTS + "(" + COLUMN_POST_ID + ")"
            + ")";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Tạo các bảng khi cơ sở dữ liệu được tạo lần đầu tiên
        db.execSQL(CREATE_TABLE_USERS);
        db.execSQL(CREATE_TABLE_POSTS);
        db.execSQL(CREATE_TABLE_PHOTOS);
        db.execSQL(CREATE_TABLE_LIKES);
        db.execSQL(CREATE_TABLE_COMMENTS);

        // Thêm dữ liệu mẫu vào bảng người dùng
        db.execSQL("INSERT INTO " + TABLE_USERS + "(" + COLUMN_USERNAME + ", " + COLUMN_USERACCNAME + ", " + COLUMN_EMAIL + ", " + COLUMN_PASSWORD + ", " + AVATAR_IMAGE + ", " + COVER_IMAGE + ", " + COLUMN_IS_ADMIN + ") VALUES ('chinhuy','user1', 'user1@example.com', 'password1', 'https://cdn.alongwalk.info/vn/wp-content/uploads/2022/10/14054048/image-100-y-tuong-avatar-cute-doc-dao-an-tuong-nhat-cho-ban-166567564813495.jpg', 'cover_url_1', 0)");
        db.execSQL("INSERT INTO " + TABLE_USERS + "(" + COLUMN_USERNAME + ", " + COLUMN_USERACCNAME + ",  " + COLUMN_EMAIL + ", " + COLUMN_PASSWORD + ", " + AVATAR_IMAGE + ", " + COVER_IMAGE + ", " + COLUMN_IS_ADMIN + ") VALUES ('yongjun','user2', 'user2@example.com', 'password2', 'https://images.fpt.shop/unsafe/filters:quality(5)/fptshop.com.vn/uploads/images/tin-tuc/175607/Originals/avt-cho-cute%20(39).jpg', 'cover_url_2', 0)");

        // Thêm dữ liệu mẫu vào bảng bài viết
        db.execSQL("INSERT INTO " + TABLE_POSTS + "(" + COLUMN_POST_GROUP + ", " + COLUMN_USER_ID + ", " + COLUMN_POST_CONTENT + ") VALUES (1, 1, '1user1의 게시물 1 내용')");
        db.execSQL("INSERT INTO " + TABLE_POSTS + "(" + COLUMN_POST_GROUP + ", " + COLUMN_USER_ID + ", " + COLUMN_POST_CONTENT + ") VALUES (1, 1, '2user1의 게시물 2 내용ádlajsdjaspdjpaspajpoadposaposadsaasdokaso kádosa sakdpokspakdkj jdiasjdpsa[dskaokd sjdpas')");
        db.execSQL("INSERT INTO " + TABLE_POSTS + "(" + COLUMN_POST_GROUP + ", " + COLUMN_USER_ID + ", " + COLUMN_POST_CONTENT + ") VALUES (1, 2, '3user2의 게시물 1 내용.\n"+"\n"+"ㅁㄴㅇㅁㄴㅇㅁ')");
        db.execSQL("INSERT INTO " + TABLE_POSTS + "(" + COLUMN_POST_GROUP + ", " + COLUMN_USER_ID + ", " + COLUMN_POST_CONTENT + ") VALUES (1, 2, '4Nội dung bài viết 2 của user2')");

        // Thêm dữ liệu mẫu vào bảng ảnh
        db.execSQL("INSERT INTO " + TABLE_PHOTOS + "(" + COLUMN_POST_ID + ", " + COLUMN_IMAGE_URL + ") VALUES (1, 'https://toplist.vn/images/800px/cu-cai-trang-230181.jpg')");
        db.execSQL("INSERT INTO " + TABLE_PHOTOS + "(" + COLUMN_POST_ID + ", " + COLUMN_IMAGE_URL + ") VALUES (2, 'https://forza.com.vn/wp-content/uploads/2021/07/cach-lam-mi-y-thom-ngon-chuan-vi-tai-nha-6.jpeg')");
        db.execSQL("INSERT INTO " + TABLE_PHOTOS + "(" + COLUMN_POST_ID + ", " + COLUMN_IMAGE_URL + ") VALUES (2, 'https://static.hubzum.zumst.com/hubzum/2022/02/10/14/3ea39a0f140c44b8b26761a93d11aa18.jpg')");
        db.execSQL("INSERT INTO " + TABLE_PHOTOS + "(" + COLUMN_POST_ID + ", " + COLUMN_IMAGE_URL + ") VALUES (2, 'https://toplist.vn/images/800px/cu-cai-trang-230181.jpg')");
        db.execSQL("INSERT INTO " + TABLE_PHOTOS + "(" + COLUMN_POST_ID + ", " + COLUMN_IMAGE_URL + ") VALUES (3, 'https://toplist.vn/images/800px/cu-cai-trang-230181.jpg')");

        db.execSQL("INSERT INTO " + TABLE_PHOTOS + "(" + COLUMN_POST_ID + ", " + COLUMN_IMAGE_URL + ") VALUES (4, 'https://toplist.vn/images/800px/chu-be-chan-cuu-230183.jpg')");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Xóa và tạo lại các bảng khi có sự thay đổi phiên bản cơ sở dữ liệu
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_POSTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PHOTOS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LIKES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COMMENTS);
        // Thêm các bảng cần thiết khác nếu cần
        onCreate(db);
    }
    public void insertUser(String username, String userAccName, String email, String password, String avatarImage, String coverImage, int isAdmin) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME, username);
        values.put(COLUMN_USERACCNAME, userAccName);
        values.put(COLUMN_EMAIL, email);
        values.put(COLUMN_PASSWORD, password);
        values.put(AVATAR_IMAGE, avatarImage);
        values.put(COVER_IMAGE, coverImage);
        values.put(COLUMN_IS_ADMIN, isAdmin);
        db.insert(TABLE_USERS, null, values);
        db.close();
    }
    public boolean isUsernameExists(String userAccname) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_USERS + " WHERE " + COLUMN_USERACCNAME + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{userAccname});
        boolean exists = cursor.moveToFirst();
        cursor.close();
        return exists;
    }

    public List<Post> getAllPosts() {
        List<Post> posts = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + TABLE_USERS + "." + AVATAR_IMAGE + ", " + TABLE_POSTS + ".*, " + TABLE_USERS + "." + COLUMN_USERNAME + ", " + TABLE_POSTS + "." + COLUMN_CREATED_AT + ", " + TABLE_POSTS + "." + COLUMN_POST_CONTENT + " FROM " + TABLE_POSTS + " JOIN " + TABLE_USERS + " ON " + TABLE_POSTS + "." + COLUMN_USER_ID + " = " + TABLE_USERS + "." + COLUMN_USER_ID, null);

        try {
            if (cursor.moveToFirst()) {
                do {
                    // Lấy các thông tin cơ bản của bài đăng
                    String avatarUrl = cursor.getString(cursor.getColumnIndexOrThrow(AVATAR_IMAGE));
                    String username = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_USERNAME));
                    String date = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CREATED_AT));
                    String content = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_POST_CONTENT));

                    // Lấy ID của bài đăng
                    int postId = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_POST_ID));

                    // Tạo một đối tượng Post
                    Post post = new Post(avatarUrl, username, date, content, null);

                    // Tạo một danh sách để lưu trữ các URL hình ảnh cho bài đăng hiện tại
                    List<String> imageUrls = new ArrayList<>();

                    // Truy vấn để lấy tất cả các URL hình ảnh từ bảng PHOTOS tương ứng với postId
                    Cursor photoCursor = db.rawQuery("SELECT * FROM " + TABLE_PHOTOS + " WHERE " + COLUMN_POST_ID + " = ?", new String[]{String.valueOf(postId)});
                    while (photoCursor.moveToNext()) {
                        String imageUrl = photoCursor.getString(photoCursor.getColumnIndexOrThrow(COLUMN_IMAGE_URL));
                        // Thêm URL hình ảnh vào danh sách
                        imageUrls.add(imageUrl);
                    }
                    photoCursor.close();

                    // Gán danh sách URL hình ảnh cho bài đăng
                    post.setImageUrls(imageUrls);

                    // Thêm đối tượng Post vào danh sách
                    posts.add(post);
                } while (cursor.moveToNext());
            }
        } finally {
            cursor.close();
            db.close();
        }
        return posts;
    }


}
