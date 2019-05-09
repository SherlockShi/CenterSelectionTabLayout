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
![CenterSelectionTabLayout](https://github.com/SherlockShi/CenterSelectionTabLayout/art/CenterSelectionTabLayout.gif)

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
### 1. use in xml files:
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
    app:normalStateTextColor="#FF009688"
    app:selectedStateTextColor="#FF00FF00"
    app:normalStateTextSize="16sp"
    app:selectedStateTextSize="17sp"/>
```

CenterSelectionTabLayout has many additional attributes, please refer to [Attributes](https://github.com/SherlockShi/CenterSelectionTabLayout#attributes)

### 2. create a class，implements `BaseItemEntity` interface，override `getItemTitle()`

```Java
public class BodyEntity implements BaseItemEntity {

    private String id;
    private String title;

    public BodyEntity(String id, String title) {
        this.id = id;
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    /**
     *
     * @return title: item title
     */
    @Override
    public String getItemTitle() {
        return title;
    }
}
```

### 3. init data
```Java
private List<BaseItemEntity> mBodyList = new ArrayList<>();
mBodyList.add(new BodyEntity("001", "头部"));
...
mBodyList.add(new BodyEntity("008", "下肢"));
```

### 4. set data and Listener
- `setData(List<BaseItemEntity>)`：set data
- `setSelectedPosition(int)`：set default selected position
- `setOnItemSelectListener(onItemSelectListener)`：item select listener
- `create()`：remember to call this method ！

```Java
CenterSelectionTabLayout mCenterSelectionTabLayout = findViewById(R.id.center_selection_tab_layout);
mCenterSelectionTabLayout
        .setData(mBodyList)
        .setSelectedPosition(0)
        .setOnItemSelectListener(new CenterSelectionTabLayout.onItemSelectListener() {
            @Override
            public void onItemSelect(int position) {
                Toast.makeText(MainActivity.this, mBodyList.get(position).getItemTitle(), Toast.LENGTH_SHORT).show();
            }
}).create();
```

# Attributes
| Attribute | function | Value Type | example |
| --- | --- | --- | --- |
| itemWidth | tab width | 1. dimension<br>2. reference | 1. 70dp<br>2. @dimen/itemWidth1 |
| selectionBackground | selection area background | reference | @drawable/bg_selected |
| selectionBackgroundWidth | selection area width | 1. dimension<br>2. reference | 1. 70dp<br>2. @dimen/selectionBackgroundWidth1 |
| selectionBackgroundHeight | selection area height | 1. dimension<br>2. reference | 1. 40dp<br>2. @dimen/selectionBackgroundHeight1 |
| normalStateTextColor | text color when normal | 1. color<br>2. reference | 1. #FF009688<br>2. @color/normalStateTextColor1 |
| selectedStateTextColor | text color when selected | 1. color<br>2. reference | 1. #FF00FF00<br>2. @color/selectedStateTextColor1 |
| normalStateTextSize | text size when normal | 1. dimension<br>2. reference | 1. 16sp<br>2. @dimen/normalStateTextSize1 |
| selectedStateTextSize | text size when selected | 1. dimension<br>2. reference | 1. 17sp<br>2. @dimen/selectedStateTextSize1 |

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