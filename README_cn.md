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
![CenterSelectionTabLayout](https://ws1.sinaimg.cn/large/006tNc79ly1g2vg6lh32pg30gu0zke83.gif)

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
    app:normalStateTextColor="#FF009688"
    app:selectedStateTextColor="#FF00FF00"
    app:normalStateTextSize="16sp"
    app:selectedStateTextSize="17sp"/>
```

CenterSelectionTabLayout 有许多额外的属性, 请参考 [Attributes](https://github.com/SherlockShi/CenterSelectionTabLayout/blob/master/README_cn.md#%E5%B1%9E%E6%80%A7)

### 2. 创建实体类，实现 `BaseItemEntity` 接口，重写 `getItemTitle()`

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

### 3. 初始化数据
```Java
private List<BaseItemEntity> mBodyList = new ArrayList<>();
mBodyList.add(new BodyEntity("001", "头部"));
...
mBodyList.add(new BodyEntity("008", "下肢"));
```

### 4. 设置数据及选中事件
- `setData(List<BaseItemEntity>)`：设置数据
- `setSelectedPosition(int)`：默认选中位置
- `setOnItemSelectListener(onItemSelectListener)`：选中事件
- `create()`：切记要调用！

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

# 属性
| 属性 | 功能 | 取值类型 | 例子 |
| --- | --- | --- | --- |
| itemWidth | tab 宽度 | 1. dimension<br>2. reference | 1. 70dp<br>2. @dimen/itemWidth1 |
| selectionBackground | 选中区域背景 | reference | @drawable/bg_selected |
| selectionBackgroundWidth | 选中区域宽度 | 1. dimension<br>2. reference | 1. 70dp<br>2. @dimen/selectionBackgroundWidth1 |
| selectionBackgroundHeight | 选中区域高度 | 1. dimension<br>2. reference | 1. 40dp<br>2. @dimen/selectionBackgroundHeight1 |
| normalStateTextColor | 正常的文字颜色 | 1. color<br>2. reference | 1. #FF009688<br>2. @color/normalStateTextColor1 |
| selectedStateTextColor | 选中的文字颜色 | 1. color<br>2. reference | 1. #FF00FF00<br>2. @color/selectedStateTextColor1 |
| normalStateTextSize | 正常的文字大小 | 1. dimension<br>2. reference | 1. 16sp<br>2. @dimen/normalStateTextSize1 |
| selectedStateTextSize | 选中的文字大小 | 1. dimension<br>2. reference | 1. 17sp<br>2. @dimen/selectedStateTextSize1 |

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