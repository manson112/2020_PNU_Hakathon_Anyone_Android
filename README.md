# 2020_PNU_Hakathon_Anyone_Android

## 개요

이 어플리케이션은 부산대학교 2020 창의 융합 해커톤 출품작입니다

자신 주변의 카페, 음식점의 포화 여부를 고객에게 보여줌으로써 고객의 시간 절약, 그리고 소상공인의 판매 촉진을 위한 어플리케이션입니다.

## Tech stack & Open-source libraries

- Minimum SDK level 23
- Kotlin based
- Koin for dependency Injection
- Jetpack
  - LiveData
  - Lifecycle : dispose of observing data when lifecycle state changes.
  - ViewModel : UI related data holder, lifecycle aware.
  - Room Persistence : construct a database using the abstract layer.
- Retrofit2 & OkHttp3 : construct the REST APIs and paging network data.
- Architecture
  - MVVM Architecture (View - ViewModel - Model)
  - Repository pattern
- Glide : loading images
- TedPermission : For checking permissions
- DaumMap


## LICENSE

```
MIT License

Copyright (c) 2019 İbrahim Süren

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```
