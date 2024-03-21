import android.app.Application;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.ValueEventListener;

public class LovePaw extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // 初始化 Firebase 实例
        FirebaseApp.initializeApp(this);
        // 获取 Realtime Database 实例
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);

        // 获取对根路径的引用
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        // 获取对特定路径的引用

        DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference().child("users");
        usersRef.child("user1").child("name").setValue("John Doe");

        // 添加数据变化监听器
        usersRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // 当数据发生变化时调用
                // 通过 dataSnapshot 可以获取最新的数据
                // 例如，dataSnapshot.getValue() 将返回该路径下的数据
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // 当读取数据出错时调用
            }
        });
    }
}
