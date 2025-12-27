docker build -t notes-cli:dev .

docker run --rm -v "${PWD}/data:/app/data" notes-cli:dev --cmd=add --text="Купить молоко" //добавляет 

docker run --rm -v "${PWD}/data:/app/data" notes-cli:dev --cmd=list //показывает список

docker run --rm -v "${PWD}/data:/app/data" notes-cli:dev --cmd=count //считает

docker run --rm -v "${PWD}/data:/app/data" notes-cli:dev --cmd=rm --id=1 //удаляет

