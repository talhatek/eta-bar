<h1 align="center"> Estimated Time of Arrival Bar </h1>
<p align="center">
  <a href="https://www.linkedin.com/in/talhatek/"><img src="https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white"></a>
  <a href="https://opensource.org/licenses/Apache-2.0"><img alt="License" src="https://img.shields.io/badge/License-Apache%202.0-blue.svg"/></a>
  <a href="https://jitpack.io/#talhatek/eta-bar"><img alt="Version" src="https://jitpack.io//v/talhatek/eta-bar.svg"/></a>
</p>
<p align="center">
<img src="/screenshots/eat_lib_example1.PNG" width="480" height="480"/>
</p>
<p align="center">
<img src="/screenshots/eat_lib_example2.PNG" width="360" height="360"/>
</p>

# How to use

Add below codes to your **root** `build.gradle` file.
```gradle
allprojects {
 repositories {
   maven { url 'https://jitpack.io' }
  }
}

```
And add a dependency code to your **module**'s `build.gradle` file.
```gradle
implementation 'com.github.talhatek:eta-bar:1.6'
```
You can call **ETABar** inside your composable content.
```kotlin
ETABar(
  elapsedPercentage = .8f,
  inactiveBarColor = Color.Gray,
  activeBarColor = Color.Yellow,
  imageBitmap = AppCompatResources.getDrawable(LocalContext.current,R.drawable.your_drawable_name)!!.toBitmap().asImageBitmap(),
  modifier = Modifier.size(200.dp)
)
```
# License
```xml
Designed and developed by 2022 talhatek
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
