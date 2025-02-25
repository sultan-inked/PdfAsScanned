# Service for pdf processing
##### Allows you to process a vector pdf, and give effect to the document that has been scanned in the scanner by overlapping different effects
### Treatment effects:
- Background tone
- Adding random noise, graininess
- Black and white filter overlay (optional)
### The background color, grain size and grayscale filter can be set in json file:
```json
{
  "gradient": {
    "color1": {
      "red": 255,
      "green": 255,
      "blue": 255,
      "alpha": 50
    },
    "color2": {
      "red": 240,
      "green": 240,
      "blue": 240,
      "alpha": 51
    }
  },
  "noise": {
    "noiseRange": 12
  },
  "grayscale": {
    "applicable": false
  }
}
```
### In the request you need to send a json file with processing settings, an example of the file described above and a PDF document with a vector content of no more than 2 megabytes


# Сервис для обработки pdf
##### Позволяет обработать векторный pdf, и придать эффект документа который был отсканирован в сканере, наложением различных эффектов
### Эффекты обработки:
- Наложение фонового оттенка
- Придание случайного шума, зернистость
- Наложение черно-белого фильтра (опционально)
### Фоновый оттенок, степень зернистости и ЧБ фильтр можно задать в json файле:
```json
{
  "gradient": {
    "color1": {
      "red": 255,
      "green": 255,
      "blue": 255,
      "alpha": 50
    },
    "color2": {
      "red": 240,
      "green": 240,
      "blue": 240,
      "alpha": 51
    }
  },
  "noise": {
    "noiseRange": 12
  },
  "grayscale": {
    "applicable": false
  }
}
```
### В запросе надо передать json файл с настройками обработки, на примере файла описанного выше и PDF документ с векторным содержимым не более 2 мегабайт