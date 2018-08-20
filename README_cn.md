# CenterSelectionTabLayout
[![](https://jitpack.io/v/SherlockShi/CenterSelectionTabLayout.svg)](https://jitpack.io/#SherlockShi/CenterSelectionTabLayout)

一个居中选中效果的 TabLayout。

# 功能
1. 居中选中
2. 滚动选择
3. 点击选择

# 文档
[English](./README.md)
中文

# 截图
![CenterSelectionTabLayout](http://7xlpfl.com1.z0.glb.clouddn.com/sherlockshi/2018-08-20-demo2.gif)

# 依赖
在根目录下的 build.gradle 文件中，添加 JitPack 仓库：

```groovy
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```

在模块的 build.gradle 文件中添加依赖：
```groovy
dependencies {
    implementation 'com.github.SherlockShi:CenterSelectionTabLayout:x.y.z'
}
```

x.y.z 即 [![](https://jitpack.io/v/SherlockShi/CenterSelectionTabLayout.svg)](https://jitpack.io/#SherlockShi/CenterSelectionTabLayout)

其它依赖方法，请参考 [JitPack](https://jitpack.io/#SherlockShi/CenterSelectionTabLayout)

# 用法
### 1. 在 xml 文件中添加布局:
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

CenterSelectionTabLayout 有许多额外的属性, 请参考 [Attributes](https://github.com/SherlockShi/CenterSelectionTabLayout/blob/master/README_cn.md#%E5%B1%9E%E6%80%A7)

### 2. `setData` 和 `setOnItemSelectListener`:
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

# 属性
| 属性 | 功能 | 取值类型 | 例子 |
| --- | --- | --- | --- |
| itemWidth | tab 宽度 | 1. dimension<br>2. reference | 1. 70dp<br>2. @dimen/itemWidth1 |
| selectionBackground | 选中区域背景 | reference | @drawable/bg_selected |
| selectionBackgroundWidth | 选中区域宽度 | 1. dimension<br>2. reference | 1. 70dp<br>2. @dimen/selectionBackgroundWidth1 |
| selectionBackgroundHeight | 选中区域高度 | 1. dimension<br>2. reference | 1. 40dp<br>2. @dimen/selectionBackgroundHeight1 |
| normalTextColor | 正常的文字颜色 | 1. color<br>2. reference | 1. #FF009688<br>2. @color/normalTextColor1 |
| selectedTextColor | 选中的文字颜色 | 1. color<br>2. reference | 1. #FF00FF00<br>2. @color/selectedTextColor1 |
| normalTextSize | 正常的文字大小 | 1. dimension<br>2. reference | 1. 16sp<br>2. @dimen/normalTextSize1 |
| selectedTextSize | 选中的文字大小 | 1. dimension<br>2. reference | 1. 17sp<br>2. @dimen/selectedTextSize1 |

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