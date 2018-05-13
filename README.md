## Google IO - Android Slices Demo


by [Jack Hughes](https://github.com/jackovt) and [Alisher Pazylbekov](https://github.com/apazylbekov)

Slices are templated views that have the ability to show and interact with content from an app from Google Search and later, places like the Google Assistant. This is a demonstration of functionality in a sample hotel and banking application. 

### Getting Started
To get started, you'll need to get the latest version of Android Studio. At the time of writing this, the latest was [Android Studio 3.2 Canary 14](https://developer.android.com/studio/preview/). In addition, make sure to download the Slice Viewer sample app found on Google's [Getting Started](https://developer.android.com/guide/slices/getting-started) site. The site also provides step by step guides on how to get Slices working on your emulator.  

### Helpful Tips
- Google has begun repackaging their support library as the "androidx" library, and you'll get the backwards compatibility by using the [androidx library](https://developer.android.com/reference/androidx/slice/Slice)
- the androidx library and the native android Slices do NOT mix, so be careful
- during the creation stage of the project, using the provided Activity & Fragment + ViewModel project set up was very helpful
- when setting up initial SliceProvider, use Android -> New -> Other -> Slice Provider so Android sets up everything for you correctly
- make sure to add any broadcast receivers you create to AndroidManifest.xml
- Slice Viewer occasionally does not update, just force close the Slice Viewer app and run again
- set up the run configurations as recommended in the [Getting Started](https://developer.android.com/guide/slices/getting-started) guide, it made testing slices much easier 
- make sure to use latest gradle versions, an easy way to be certain or to find specific dependencies is to look in the [Maven Repository](https://mvnrepository.com/search?q=androidx)
	- `implementation 'androidx.slice:slices-core:1.0.0-alpha1'` and `implementation 'androidx.slice:slices-builders:1.0.0-alpha1'` caused builds to fail, make sure to update to `androidx.slice:slice`