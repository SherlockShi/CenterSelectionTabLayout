# CenterSelectionTabLayout
[![](https://jitpack.io/v/SherlockShi/CenterSelectionTabLayout.svg)](https://jitpack.io/#SherlockShi/CenterSelectionTabLayout)

A TabLayout with a centered selection effect.

# Function
1. centered selection
2. scroll to select
3. click to select

# Document
English
[中文](./README_cn.md)

# Screenshot
![CenterSelectionTabLayout](http://7xlpfl.com1.z0.glb.clouddn.com/sherlockshi/2018-08-20-demo2.gif)

# Dependency
Add the JitPack repository to your root build.gradle at the end of repositories:

```groovy
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```

Add the dependency in your module build.gradle:
```groovy
dependencies {
    implementation 'com.github.SherlockShi:CenterSelectionTabLayout:x.y.z'
}
```

x.y.z is [![](https://jitpack.io/v/SherlockShi/CenterSelectionTabLayout.svg)](https://jitpack.io/#SherlockShi/CenterSelectionTabLayout)

other dependency method, please refer to [JitPack](https://jitpack.io/#SherlockShi/CenterSelectionTabLayout)

# Usage
##### 1. use in xml files:
```xml
<com.sherlockshi.widget.CenterSelectionTabLayout
    android:id="@+id/center_selection_tab_layout"
    android:layout_width="match_parent"
    android:layout_height="60dp"
    app:backgroundColor="#FF3F51B5"
    app:itemWidth="70dp"
    app:selectionBackground="@drawable/bg_selected"
    app:selectionBackgroundWidth="70dp"
    app:selectionBackgroundHeight="40dp"
    app:normalTextColor="#FF009688"
    app:selectedTextColor="#FF00FF00"
    app:normalTextSize="16sp"
    app:selectedTextSize="17sp"/>
```

CenterSelectionTabLayout has many additional attributes, please refer to [Attributes](https://github.com/SherlockShi/CenterSelectionTabLayout#attributes)

##### 2. `setData` and `setOnItemSelectListener`:
```java
CenterSelectionTabLayout mCenterSelectionTabLayout = findViewById(R.id.center_selection_tab_layout);
mCenterSelectionTabLayout.setData(mTitleList);
mCenterSelectionTabLayout.setOnItemSelectListener(new CenterSelectionTabLayout.onItemSelectListener() {
    @Override
    public void onItemSelect(int position) {
        Toast.makeText(MainActivity.this, mTitleList.get(position), Toast.LENGTH_SHORT).show();
    }
});
```

# Attributes
| Attribute | function | Value Type | example |
| --- | --- | --- | --- |
| itemWidth | tab width | 1. dimension<br>2. reference | 1. 70dp<br>2. @dimen/itemWidth1 |
| selectionBackground | selection area background | reference | @drawable/bg_selected |
| selectionBackgroundWidth | selection area width | 1. dimension<br>2. reference | 1. 70dp<br>2. @dimen/selectionBackgroundWidth1 |
| selectionBackgroundHeight | selection area height | 1. dimension<br>2. reference | 1. 40dp<br>2. @dimen/selectionBackgroundHeight1 |
| normalTextColor | text color when normal | 1. color<br>2. reference | 1. #FF009688<br>2. @color/normalTextColor1 |
| selectedTextColor | text color when selected | 1. color<br>2. reference | 1. #FF00FF00<br>2. @color/selectedTextColor1 |
| normalTextSize | text size when normal | 1. dimension<br>2. reference | 1. 16sp<br>2. @dimen/normalTextSize1 |
| selectedTextSize | text size when selected | 1. dimension<br>2. reference | 1. 17sp<br>2. @dimen/selectedTextSize1 |

# ProGuard
```groovy
-keep class com.sherlockshi.widget.** { *; }
```

# License
```
Copyright 2018 SherlockShi

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```