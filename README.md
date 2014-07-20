Expandable Panel Android Library
================================
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

Import ExpandablePanel dependency
--------------------------------
TBA - Deploy to maven central not completed atm.


