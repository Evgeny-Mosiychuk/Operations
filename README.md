# Operations

## Run application

1. Clone the repository:

    ```bash
    git clone https://github.com/Evgeny-Mosiychuk/Operations
    ```
2. Navigate to the project directory:

    ```bash
    cd Operations
    ```
3. Build the application:

    For Unix:

    ```bash
    ./mvnw clean install
    ```

    For Windows:

    ```bash
    mvnw.cmd clean install
    ```

4. Run the application:

    ```bash
    java -jar target/Paysonix-0.0.1-SNAPSHOT.jar
    ```


## Сборка докер образа

docker build -t paysonix:latest

## Запуск контейнера

docker run --rm -p 8080:8080 paysonix:latest
