## Google IO - Android Slices Demo


by [Jack Hughes](https://github.com/jackovt) and [Alisher Pazylbekov](https://github.com/apazylbekov)

Slices are templated views that have the ability to show and interact with content from an app from Google Search and later, places like the Google Assistant. This is a demonstration of functionality in a sample hotel and banking application. 

### Getting Started
- You'll need to get the latest version of Android studio to use Slices. At the Time of writing this, the latest was Android Studio 3.2 Canary 14, so get that version or newer.
- Google has begun repackaging their suppor library as the "androidx" library, and you'll get the backwards compatibility by using the androidx library. The androidx library and the native android Slices do NOT mix, so be careful
- using Activity + Fragment + ViewModel
- when setting up initial SliceProvider, use Android -> new file -> other -> SliceProvider, Android sets up everything for you correctly
- make sure to add broadcast receiver to AndroidManifest.xml
- SliceViewer update issues, force close and run again
- run configurations, very useful easier than ADB
- documentation inconsistencies, make sure using androidx, link to https://developer.android.com/reference/androidx/slice/Slice 
- gradle versions, link to maven repository

- Why Use Them
    - Re-engage with users outside of the app
    - Provide relevant, targeted actions and information on demand
- How Do I create Them
    - Code Demo
- How Do I Preview My Slice
    - Slice Preview App
- Pros and Cons
	- Pro: Slices can be displayed in multiple places, getting more eyes in commonly used utilities, like Google Search
	- Con: Restricted to templates for consistancy
	- Pro: Templates are fairly rich, allow linking and callbacks to app
- Conclusion