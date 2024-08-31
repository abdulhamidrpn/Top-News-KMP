# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

-keep class com.rpn.newskmpapp.data.remote.dto.* { *; }
-keep class com.rpn.newskmpapp.domain.model.* { *; }
-keepattributes *Annotation*
-dontwarn android.arch.**
-dontwarn com.google.**
-dontwarn javax.annotation.**
-dontwarn org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement
-dontwarn org.apache.http.**
-dontwarn java.lang.invoke.StringConcatFactory
# Or keep all classes in a package
-keep class com.rpn.newskmpapp.** { *; }

-whyareyoukeeping class com.rpn.newskmpapp.** { *; }
-dontobfuscate
