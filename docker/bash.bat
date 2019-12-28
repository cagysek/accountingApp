REM spusti prikazovou radku v kontejneru
docker exec -it php56vp bash -c 'cd /var/www/html; exec "${SHELL:-sh}"'
