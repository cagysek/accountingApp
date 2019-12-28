# Docker Apache + PHP 5.6 + Composer + Node.JS + NPM + Yarn

Před použitím containeru je nutné vytvořit lokální soubor:
- docker-compose.override.yml ze souboru docker-compose.override.EXAMPLE.yml


Spuštění containeru: bin/docker_start
nebo ručně následujícím příkazem v adresáři docker:
docker-compose up -d --build

Připojení do konzole containeru serveru: bin/docker_bash
nebo ručně následujícím příkazem v adresáři docker:
docker exec -it php56vp bash
