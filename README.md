## 1. Environment & project setup

1. (+) Стварыць рэпазіторый праекту на GitHub. Уся праца з зыходнікамі павінна весціся ў адпаведнасці з GitFlow.

2. (+) Стварыць праект, накіраваны на платформу Android 6.0

3. (+) Апрацоўка ўсіх runtime-permissions павінна выконвацца ў адпаведнасці з рэкамендацыямі Google.

4. (+) Наладзіць версіяніраванне праекту згодна з SEMVER (Semantic Versioning), дзе MAJOR версія — заўсёды 0, MINOR павялічваецца з кожным рэлізам (у нашым выпадку рэлізам будзе лічыцца здача лабараторнай), PATCH — парадкавы нумар каміту пасля апошняга рэлізу. Версія (і назва праекту) павінна адлюстроўвацца у назве APK файлу пры зборцы. *(e.g. com.example-0.1.1.apk)*. **Увага: версія праекту павінна генеравацца аўтаматычна, а не мяняцца праграмістам з кожным камітам.**

5. (+) Рэліз версія APK файлу павінна быць падрыхтаваная да публікацыі на Google Play: файл павінен быць падпісаны пры дапамозе ўласнага сертыфікату. Усе налады гэтага павінны прысутнічаць у праекце, а не ўводзіцца кожны раз праграмістам праз опцыю *Build->Generate Signed APK*

6. (+) Праграма павінна мець на дадзены момант адзіны экран, на якім павінна адлюстоўвацца версія праекту і IMEI тэлефону. (Пры запуску на эмулятары дазваляецца, што IMEI можа быць некарэктным, але пры запуску на рэальным дэвайсе такіх праблем быць не павінна).

7. (+) Праграма павінна мець дзве варыяцыі ў межах аднаго праекту (не на розных галінах Git, а адначасова): асноўную версію і версію для распрацоўцы. На дадзены момант паміж імі існуюць наступныя адрозненні: асноўная версія працуе толькі ў партрэтнай арыентацыі (пры павароце тэлефону/планшэту арыентацыя не павінна змяняцца), а версія для распрацоўцы павінна мяняць сваю арыентацыю пры павароце дэвайсу; акрамя таго, версія праекту ў варыяцыі для распрацоўцы павінна дапаўняцца суфіксам `-dev`. *(e.g. 0.1.1-dev)*

## 2. Activities, fragments and navigation

1. Правесці міграцыю праекту. Замест выкарыстання Android Support Library, праект павінен працаваць з бібліятэкаю AndroidX (https://developer.android.com/jetpack/androidx/).

2. Рэалізаваць на галоўнай старонцы праграмы адзін з двух рэкамендаваных патэрнаў навігацыі: **Navigation drawer** (https://material.io/design/components/navigation-drawer.html) ці **Bottom navigation** (https://material.io/design/components/bottom-navigation.html). Выкарыстоўваць Navigation Architecture Components library (https://developer.android.com/topic/libraries/architecture/navigation/).

3. У арганізаванай сістэме навігацыі дадаць старонку профіля карыстальніка. **Зрабіць адэкватны дызайн гэтай старонкі!!!** На ёй павінна знаходзіцца наступная інфармацыя: аватар, імя, прозвішча, тэлефон, email адрас. Усе дадзеныя павінны выводзіцца ў TextView. Павінна існаваць кнопка, якая будзе пераводзіць старонку ў рэжым рэдактавання. У гэтым рэжыме карыстальнік мае магчымасць змяніць усе дадзеныя профілю. Рэалізаваць магчымасць зрабіць фотаздымак ці выбраць выяву з галерэі тэлефону і паставіць яе ў якасці аватару карыстальніка. Усе дадзеныя павінны захоўвацца паміж запускамі праграмы.

4. Дадаць яшчэ 2-3 пустыя старонкі ў сістэму навігацыі.

5. Рэалізаваць магчымасць апынуцца на любой з даданых старонак пры адчыненні URL віду *sdapp://by.myapp/page/N*, дзе N вызначае нумар старонкі, на якую трапіць карыстальнік. (Тэставаць пры дапамозе каманды ADB `am start -W -a android.intent.action.VIEW -d "sdapp://by.myapp/page/N" by.your.app`)

6. Дадаць у AppBar кнопку, пры націсканні якой будзе адчыняцца новая старонка About, у якую трэба перанесці інфармацыю пра IMEI дэвайса і версію праграмы.
