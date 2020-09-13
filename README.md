# Система распознавния дефектов ЛЭП по фотографиям с беспилотника

## Pwl-server

Север для хранения информации об обходах ЛЭП, составления маршрутов и мониторинга результатов.

Модуль служит для взаимодействия всех компонентов системы

#### Первый запуск

При перво запуске рекомендуется заменить в properties

            jpa:
                hibernate:
                    ddl-auto: none
            
На create, в после первого запуска венуть none

Если есть необходимость заполнить базу тестовыми данными, раскомментировать @Component 
на миграции Migration.java

    OAS 3.0 доступно по ссылке http://localhost:8080/swagger-ui.html


## Graph server

Микросервис по взаимодействию Neo4j, модификации графа опор, ЛЭП и получение маршуртов

    OAS 3.0 доступно по ссылке http://localhost:8081/swagger-ui.html


## YOLOv5
Обучается на размеченных данных, мы использовали 2 датасета одновременно. 
Один взяли из открытых источников, второй разметили сами вручную при помощи labelImg.
Для обучения запускать скрипт go, он обучает 100 этох с размером бача 8. Как архитектуру использовали yolov5s для ускорения обучения. 

Из-за ограничений github нужно склеить веса в папке weights last.pt и best.pt
Склеить веса можно конмадой cat 
    
    name.pt* > name.pt

Для работы используется файл YOLO_demon.py, он слушает 5000 порт и ждет файлы от фронтэнда.
