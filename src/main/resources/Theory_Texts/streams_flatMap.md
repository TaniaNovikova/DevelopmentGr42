### 🔹 **Что делает `flatMap()`?**
`flatMap(Function<T, Stream<R>>)` используется для преобразования **вложенных структур данных**
(например, `List<List<T>>`) в **плоский поток** (`Stream<T>`).

🔹 **Основная идея** – каждый элемент преобразуется в `Stream<R>`,
а затем все эти стримы объединяются в один.

---

### **📌 Пример 1: Объединение вложенных списков**
👉 У нас есть `List<List<Integer>>`, и мы хотим получить `List<Integer>`.

```
List<List<Integer>> listOfLists = List.of(
    List.of(1, 2, 3),
    List.of(4, 5),
    List.of(6, 7, 8, 9)
);

List<Integer> flattenedList = listOfLists.stream()
    .flatMap(List::stream) // Разворачиваем List<Integer> в поток
    .collect(Collectors.toList()); // Собираем в список

System.out.println(flattenedList); 
// Ожидаемый результат: [1, 2, 3, 4, 5, 6, 7, 8, 9]
```

🔍 **Как это работает?**
- `listOfLists.stream()` создает поток списков `Stream<List<Integer>>`.
- `flatMap(List::stream)` берет каждый список и превращает его в `Stream<Integer>`.
- Все эти потоки объединяются в один.

---

### **📌 Пример 2: Разбиение строк на отдельные слова**
👉 У нас есть `List<String>`, и мы хотим получить `List<String>` со всеми словами.

```
List<String> sentences = List.of("Hello world", "Java Stream API", "flatMap example");

List<String> words = sentences.stream()
    .flatMap(sentence -> Arrays.stream(sentence.split(" "))) // Разбиваем строки на слова
    .collect(Collectors.toList());

System.out.println(words); 
// Ожидаемый результат: ["Hello", "world", "Java", "Stream", "API", "flatMap", "example"]
```

🔍 **Что происходит?**
- Каждую строку (`"Hello world"`) мы разбиваем на массив `["Hello", "world"]`.
- `Arrays.stream(...)` превращает массив в поток `Stream<String>`.
- `flatMap(...)` объединяет все эти потоки в один.

---

### **📌 Пример 3: Обработка Optional**
👉 `flatMap()` полезен при работе с `Optional<T>`.

```
Optional<String> optional = Optional.of("Hello");

Optional<String> result = optional
    .flatMap(s -> Optional.of(s.toUpperCase())); 

System.out.println(result); 
// Ожидаемый результат: Optional[HELLO]
```

🔍 **Разница между `map()` и `flatMap()` в Optional:**
- `map()` просто заворачивает результат в `Optional<Optional<R>>`.
- `flatMap()` разворачивает его, убирая вложенность.

---

### **📌 Итог**
✔ `flatMap()` нужен, когда работаешь со вложенными структурами и хочешь **развернуть их в один поток**.  
✔ Убирает **избыточные уровни вложенности**, делая обработку данных удобной.  
✔ Используется для работы с **`List<List<T>>`**, строками, `Optional`, и др.  

