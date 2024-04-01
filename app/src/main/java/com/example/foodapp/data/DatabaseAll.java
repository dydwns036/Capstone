package com.example.foodapp.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.foodapp.Menu1Fragment;
import com.example.foodapp.model.TaiKhoan;
import com.example.foodapp.model.Truyen;

public class DatabaseAll extends SQLiteOpenHelper {

    private static String DATABASE_NAME = "doctruyen";
    private static String TABLE_ACCOUNT = "taikhoan";
    private static String ID_ACCOUNT = "idtaikhoan";
    private static String ACC_NAME = "tentaikhoan";
    private static String PASSWORD = "matkhau";
    private static String PHAN_QUYEN = "phanquyen";
    private static String EMAIL = "email";
    private static int VERSION = 1;

    private static String TABLE_POST = "truyen";
    private static String ID_POST = "idtruyen";
    private static String POST_NAME = "tieude";
    private static String POST_CONTENT = "content";
    private static String IMAGE = "anh";


    private Context context;

    private String SQLQuery = "CREATE TABLE "+ TABLE_ACCOUNT +" ( "+ID_ACCOUNT+" INTEGER PRIMARY KEY AUTOINCREMENT, "
            +ACC_NAME+" TEXT UNIQUE, "
            +PASSWORD+" TEXT, "
            +EMAIL+" TEXT, "
            + PHAN_QUYEN+" INTEGER) ";

    private String SQLQuery1 = "CREATE TABLE "+ TABLE_POST +" ( "+ID_POST+" integer primary key AUTOINCREMENT, "
            +POST_NAME+" TEXT UNIQUE, "
            +POST_CONTENT+" TEXT, "
            +IMAGE+" TEXT, "+ID_ACCOUNT+" INTEGER , FOREIGN KEY ( "+ ID_ACCOUNT +" ) REFERENCES "+
            TABLE_ACCOUNT+"("+ID_ACCOUNT+"))";

    private String SQLQuery2 = "INSERT INTO TaiKhoan VAlUES (null,'admin','admin','admin@gmail.com',2)";
    private String SQLQuery3 = "INSERT INTO TaiKhoan VAlUES (null,'khanh','khanh','khanh@gmail.com',1)";
    private String SQLQuery11 = "INSERT INTO TaiKhoan VAlUES (null,'su','su','su@gmail.com',1)";

    private String SQLQuery4 = "INSERT INTO truyen VALUES (null,'Rùa và Thỏ','Phần 1:\n" +
            "\n" +
            "Ý nghĩa câu chuyện phần 2: Biết sai và sửa sai là một đức tính tốt, đó chính là lý do giúp anh chàng thỏ giành được chiến thắng ở cuộc đua thứ 2. Mẹ hãy giải thích cho bé hiểu rằng trong công việc hàng ngày giữa một người chậm, cẩn thận và đáng tin cậy với một người nhanh nhẹn, đáng tin cậy, chắc chắn người nhanh nhẹn sẽ được trọng dụng hơn nhiều và họ sẽ tiến xa hơn trong học tập, cũng như trong cuộc sống. Cha mẹ hãy giúp bé hiểu rõ thông điệp chậm và chắc là điều tốt, nhưng nhanh và đáng tin cậy sẽ tốt hơn rất nhiều.','https://toplist.vn/images/800px/rua-va-tho-230179.jpg',1)";
    private String SQLQuery5 = "INSERT INTO truyen VALUES (null,'Củ cải trắng','Mùa đông đã đến rồi trời lạnh buốt, Thỏ con không có gì để ăn cả. Thỏ con mặc áo vào rồi ra ngoài kiếm thức ăn. Nó đi mãi đi mãi cuối cùng cũng tìm được hai củ cải trắng. Thỏ con reo lên:\n" +
            "\n" +

            "Ý nghĩa câu chuyện: Khi cho đi bạn sẽ nhận lại được nhiều hơn những thứ mình có.','https://toplist.vn/images/800px/cu-cai-trang-230181.jpg',1)";
    private String SQLQuery6 = "INSERT INTO truyen VALUES (null,'Dê đen và dê trắng','Dê đen và Dê trắng cùng sống trong một khu rừng. Hàng ngày, cả hai thường đến uống nước và tìm cái ăn ở trong khu rừng quen thuộc.\n" +
            "\n" +

            "Ý nghĩa câu chuyện: Qua câu chuyện ngụ ngôn trên, bạn có thể truyền tải nhiều thông điệp khác nhau cho bé hiểu. Chẳng hạn như biết cách ứng xử trước các tình huống khó, nguy hiểm, lạc quan và bản lĩnh để xử lý vấn đề.','https://toplist.vn/images/800px/de-den-va-de-trang-230182.jpg',1)";
    private String SQLQuery7 = "INSERT INTO truyen VALUES (null,'Chú bé chăn cừu','Một chú bé chăn cừu cho chủ thả cừu gần một khu rừng rậm cách làng không xa lắm. Chăn cừu được ít lâu, chú cảm thấy công việc chăn cừu thực là nhàm chán. Tất cả mọi việc chú có thể làm để giải khuây là nói chuyện với con chó hoặc thổi chiếc sáo chăn cừu của mình.\n" +
            "\n" +

            "Ý nghĩa câu chuyện: Nói dối là một tật xấu. Người hay nói dối ngay cả khi nói thật cũng không ai tin.','https://toplist.vn/images/800px/chu-be-chan-cuu-230183.jpg',1)";
    private String SQLQuery8 = "INSERT INTO truyen VALUES (null,'Cậu bé chăn cừu và cây đa cổ thụ','Ngày xửa ngày xưa, xưa lắm rồi khi mà muôn thú, cây cỏ, con người còn trò chuyện được với nhau. Trên đồng cỏ rậm ven khu làng có một loài cây gọi là cây đa. Đó là một thứ cây to, khỏe, lá của nó rậm rạp đến nỗi không một tia nắng nào có thể lọt qua được. Vào những ngày trời nắng nóng người ta thường nghỉ chân một lát và trò chuyện hàn huyên cùng cây dưới bóng cây mát rượi. Mọi người ai cũng biết rằng cây đa rất thông thái vì cây đã có tuổi, đã từng trải.\n" +
            "\n" +

            "Ý nghĩa câu chuyện: Có thể cậu bé chăn cừu không phải ngay sau đó sẽ trở nên khiêm tốn, học hỏi luôn được nhưng rõ ràng là cậu đã nhận ra người ta không thể sống lẻ loi được.','https://toplist.vn/images/800px/cau-be-chan-cuu-va-cay-da-co-thu-230184.jpg',1)";



    public DatabaseAll(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION);
        this.context = context;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQLQuery);
        db.execSQL(SQLQuery1);
        db.execSQL(SQLQuery2);
        db.execSQL(SQLQuery3);
        db.execSQL(SQLQuery4);
        db.execSQL(SQLQuery5);
        db.execSQL(SQLQuery6);
        db.execSQL(SQLQuery7);
        db.execSQL(SQLQuery8);
        db.execSQL(SQLQuery11);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void AddTaiKhoan(TaiKhoan taiKhoan){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ACC_NAME,taiKhoan.getmTenTaiKhoan());
        values.put(PASSWORD,taiKhoan.getmMatKhau());
        values.put(EMAIL,taiKhoan.getmEmail());
        values.put(PHAN_QUYEN,taiKhoan.getmPhanQuyen());

        db.insert(TABLE_ACCOUNT,null,values);

        db.close();
        //Log.e("Add acc : ","OK");
    }


    public Cursor getData(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from "+TABLE_ACCOUNT , null );
        return res;
    }




    public void AddTruyen(Truyen truyen){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(POST_NAME,truyen.getTenTruyen());
        values.put(POST_CONTENT,truyen.getNoiDung());
        values.put(IMAGE,truyen.getAnh());
        values.put(ID_ACCOUNT,truyen.getID_TK());

        db.insert(TABLE_POST,null,values);
        db.close();
        Log.e("Add POST: ","OK");
    }


    //데이터베이스에서 최신 5 개의 게시물을 가져와 메인 화면에 표시합니다
    //5개의 가장 좋아하는 게시물을 가져오기 위해 데이터베이스에 좋아요 수 열을 추가합니다
    public Cursor getData1(){

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor res =  db.rawQuery( "select * from "+TABLE_POST+" ORDER BY "+ID_POST+" DESC LIMIT 5" , null );
        return res;
    }


    //모든 게시물 가져오기
    public Cursor getData2(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM "+TABLE_POST,null);
        return res;
    }
    public Cursor getData3(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_POST+" ORDER BY "+ID_POST,null);
        return res;
    }

    //Delete with  id = i
    public int Delete(int i){
        SQLiteDatabase db = this.getReadableDatabase();

        int res = db.delete("truyen",ID_POST+" = "+i,null);
        return res;

    }
}
