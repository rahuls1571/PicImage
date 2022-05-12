# PicSum-Image

   API Use
   {
   
        https://picsum.photos/v2/list?page=0
   
   }
   
   Features 
   {
   
                 1. The app get images and display in the Recycler View.
                 2. Use MVVM with Kotlin to implement pagination.
                 3. Use retrofit for network communication.
                 4. Light and Dark Mode.
                 5. Paging 3 for Pagination.
                 6. Bottom loader state. 
                 7. Show image load progress.
                 8. Offline support (cashed images will be visible in no internet connection senecio).
                  
   }
   
   dependencies
   {

           // Retrofit
          implementation "com.squareup.retrofit2:retrofit:$retrofit_version"
          implementation "com.squareup.retrofit2:converter-gson:$retrofit_version"
          // Glide
          implementation "com.github.bumptech.glide:glide:$glide_version"
          annotationProcessor "com.github.bumptech.glide:compiler:$glide_version"
          // Paging
          implementation "androidx.paging:paging-runtime:$paging_version"
          // ViewModel
          implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_version")
          // LiveData
          implementation("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycle_version")
   
   
   }



 
   ext
   {
   
          lifecycle_version = "2.3.1"
          paging_version    = "3.0.0-beta01"
          glide_version     = "4.12.0"
          retrofit_version  = "2.9.0"
   
   
   }


# App Image

<p align="center">
  <img width="300" height="600" src="https://user-images.githubusercontent.com/46783288/131085238-17fb4236-93b0-457e-bd40-e7619ea78cd8.jpg">
</p>


<p align="center">
  <img width="300" height="600" src="https://user-images.githubusercontent.com/46783288/131085225-5e39b3ac-618c-4b79-8025-9c6cea5d3a8a.jpg">
</p>

<p align="center">
  <img width="300" height="600" src="https://user-images.githubusercontent.com/46783288/131085245-43445d97-5e7d-4b24-83c5-2bd877e56c58.jpg">
</p>

<p align="center">
  <img width="300" height="600" src="https://user-images.githubusercontent.com/46783288/131085761-73c5740e-19e5-4e52-8a6a-158463c220bd.jpg">
</p>

<p align="center">
  <img width="300" height="600" src="https://user-images.githubusercontent.com/46783288/131085772-aa45e37b-0b04-47e5-8324-bd21b244dfc9.jpg">
</p>




