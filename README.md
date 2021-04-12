# Home_Training_Application

홈트레이닝을 도와주는 애플리케이션으로 AI 스피커로 사용자가 설정한 운동 정보를 전달해 사용자가 보다 편안하게 운동할 수 있도록 도와준다.


![image](https://user-images.githubusercontent.com/37439958/114421362-321ab700-9bf0-11eb-9269-578f8e473ae7.png)

# Gradle
> app build.gradle

    implementation 'com.google.firebase:firebase-core:16.0.1'
    compile 'com.google.firebase:firebase-firestore:15.0.0'
    implementation 'com.google.firebase:firebase-storage:16.0.1'
    implementation 'com.firebaseui:firebase-ui-storage:0.6.0'
    implementation 'com.google.firebase:firebase-database:15.0.1'
    
# Firebase에서 운동 정보 가져오기
```java
  public FirebaseFirestore db;
  ...
  db = FirebaseFirestore.getInstance();
  
  db.collection(part)
      .get()
      .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
          @Override
          public void onComplete(@NonNull Task<QuerySnapshot> task) {
            if (task.isSuccessful()) {
              for (final DocumentSnapshot document : task.getResult()) {
                ListViewItem item = new ListViewItem(document.getId()+".jpg",document.getString("name"),document.getString("pose"),document.getString("way"));
                data.add(item);
                adapter.addItem(item.getIcon(),item.getTitle(),item.getDesc(),item.getWay());
              }
            } else { 
              Log.d(TAG, "Error getting documents: ", task.getException());
            }
            listview.setAdapter(adapter);
          }
      });
```
