Expandable Panel Android Library
================================

![Demo Screenshot 1](https://raw.githubusercontent.com/JorgeCastilloPrz/ExpandablePanel/master/app/src/main/res/raw/sample.gif)

Details
-------

This Android library implements the expand by sliding logic for a top view in a two children view composition. It supports ``Android SDK 2.1 (Eclair)`` as minimum. 

ExpandablePanel library brings a custom view class called `ExpandablePanelView` to the final user. It implements the needed logic for integrating the expandable logic into your own Android application.

Custom Attributes
-------------

ExpandablePanel lib allows you to customize the following properties:

* `expandablepanel:completionPercent`: % of the parent's height where you want the autocomplete animation to begin working.
* `expandablepanel:completeExpandAnimationSpeed`: Speed for the autocomplete animation.
* `expandablepanel:completeShrinkAnimationSpeed`: Speed for the autoshrink animation.

Usage
-----

In order to make it work, you will need to use `ExpandablePanelView` class into your Android XML Layout.

* 1. Add `ExpandablePanelView` to the layout.
* 2. Add two children views to the ExpandablePanelView XML element.
* 3. `ExpandablePanelView` extends `RelativeLayout`, so you will need to give an android id to the top view and setup the `android:below` attribute in the bottom one. 
* 4. Set the `xmlns:draggable_view="http://schemas.android.com/apk/res-auto"` if you are going to use any of the cusom attributes.

Code sample:

    <RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:tools="http://schemas.android.com/tools"
        xmlns:expandablepanel="http://schemas.android.com/apk/res-auto"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        tools:context=".MainActivity">

        <com.jorgecastilloprz.expandablepanel.ExpandablePanelView
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:background="@android:color/darker_gray"
            expandablepanel:completionPercent="0.8"
            expandablepanel:completeExpandAnimationSpeed="150"
            expandablepanel:completeShrinkAnimationSpeed="200">
    
            <ImageView
                android:id="@+id/topLayout"
                android:layout_width="match_parent"
                android:layout_height="250dp"
                android:src="@drawable/nightbackground"
                android:scaleType="centerCrop"/>
    
            <ImageView
                android:background="@color/material_pink"
                android:layout_width="match_parent"
                android:layout_height="match_parent"
                android:layout_below="@+id/topLayout"/>
    
        </com.jorgecastilloprz.expandablepanel.ExpandablePanelView>

    </RelativeLayout>
    
Use `ExpandableListener` if you want your class to be able to get expandable callbacks. Following methods are offered to the user:

* `onExpandingStarted`: Dispatched when the user starts expanding the view.
* `onExpandingFinished`: Dispatched when autocomplete expanding animation is finished.
* `onShrinkStarted`: Dispatched when the user starts shrinking the view.
* `onShrinkFinished`: Dispatched when autocomplete shrinking animation is finished.
* `onExpandingTouchEvent`: Dispatched meanwhile the user is dragging to expand or shrink the view. This one is very useful if you want to map touch coordinates to your class and be able to use them for creating cool combined animations.

Import ExpandablePanel dependency
---------------------------------
Add the next code to your build.gradle project dependencies:

    dependencies {
        compile 'com.github.jorgecastilloprz:expandablepanel:1.0.0@aar'
    }

Set the mavenCentral repo into the external build.gradle:

    buildscript {
        repositories {
            mavenCentral()
        }
        dependencies {
            classpath 'com.android.tools.build:gradle:0.12.+'
        }
    }
    
    allprojects {
        repositories {
            mavenCentral()
        }
    }

License
-------

    Copyright 2014 Jorge Castillo Pérez

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

[1]: http://188.226.233.205/ExpandablePanel/sample.gif
