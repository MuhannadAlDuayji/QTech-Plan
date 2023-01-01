FROM node:18.12.1-slim

WORKDIR /app

COPY . .

RUN npm install -g svgo

ENV API_URL=localhost:3000

RUN npm install

EXPOSE 3000

ENTRYPOINT ["npm", "start"]
