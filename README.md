# API de mercado

### Esse projeto possui uma documentação dinâmica feita em Swagger.
### Para acessar, acesse a URL
```http://localhost:8080/swagger-ui.html```

### Requisitos
- JDK

### Como rodar o projeto
#### Abrir o projeto no seu editor de preferência (Eclipse, IntelliJ, VS Code) e iniciar a classe principal ``SpringEvaluation4Application.java``.
#### As requisições do tipo ``GET`` podem ser testadas através do navagador, porém para testar a API em apenas um lugar, recomendam-se os ‘softwares’ ``Postman`` e ``Insomnia``.

## Endpoints

# ``/persons``

## ``POST`` ``/persons``
### Realiza o cadastro de uma pessoa.

Exemplo de requisição
```json
{
	"name": "Gabriel",
	"document": "38549174904",
	"wage": 3500,
	"gender": "M",
	"addresses": [
		{
			"country": "Brasil",
			"state": "Paraná",
			"city": "SJP",
			"zipCode": 83015717,
			"street": "Rua das flores"
		},
		{
			"country": "Brasil",
			"state": "Paraná",
			"city": "Pinhais",
			"zipCode": 83010561,
			"street": "Rua dos cravos"
		}
	]
}
```

Exemplo de resposta
```json
{
  "id": 1,
  "name": "Gabriel",
  "document": "385.491.749-04",
  "wage": "R$ 3.500,00",
  "gender": "M",
  "addresses": [
    {
      "country": "SJP",
      "state": "Paraná",
      "city": "SJP",
      "zipCode": "83015-717",
      "street": "Rua das flores"
    },
    {
      "country": "Pinhais",
      "state": "Paraná",
      "city": "Pinhais",
      "zipCode": "83010-561",
      "street": "Rua dos cravos"
    }
  ]
}
```

## ``GET`` ``/persons``
### Retorna uma lista com todas as pessas.

Exemplo de resposta
```json
[
  {
    "id": 1,
    "name": "Gabriel",
    "document": "385.491.749-04",
    "wage": "R$ 3.500,00",
    "gender": "M",
    "addresses": [
      {
        "country": "SJP",
        "state": "Paraná",
        "city": "SJP",
        "zipCode": "83015-717",
        "street": "Rua das flores"
      },
      {
        "country": "Pinhais",
        "state": "Paraná",
        "city": "Pinhais",
        "zipCode": "83010-561",
        "street": "Rua dos cravos"
      }
    ]
  },
  {
    "id": 2,
    "name": "Ana",
    "document": "594.602.479-54",
    "wage": "R$ 5.000,00",
    "gender": "F",
    "addresses": [
      {
        "country": "SJP",
        "state": "Paraná",
        "city": "SJP",
        "zipCode": "83015-717",
        "street": "Rua das flores"
      }
    ]
  }
]
```

#### Esse endpoint possuí vários filtros e ordenações:

- Filtrar por nome

``/persons?name=Gabriel``

- Filtrar por sexo

``/persons?gender=F``

- Filtrar por nome e sexo.

``/persons?name=Gabriel&gender=M``

- Ordenar por todos os parâmetros possíveis,
  para isso deve-se indicar se é ascendente ou descendente.

Os parâmetros possíveis são:
- name
- document
- wage
- gender

``/persons?sort=name,asc``
``/persons?sort=name,desc``
``/persons?sort=wage,asc``
``/persons?sort=wage,desc``
``/persons?sort=name,asc&sort=wage,asc``
``/persons?sort=name,desc&sort=wage,desc``

- Também é possível juntar a ordenação com a filtragem.

``/persons?name=Gabriel&sort=name,asc&sort=wage,desc``

## ``GET`` ``/persons/{id}``
### Retorna uma única pessoa pelo ID

Exemplo de resposta
```json
{
  "id": 2,
  "name": "Ana",
  "document": "594.602.479-54",
  "wage": "R$ 5.000,00",
  "gender": "F",
  "addresses": [
    {
      "country": "SJP",
      "state": "Paraná",
      "city": "SJP",
      "zipCode": "83015-717",
      "street": "Rua das flores"
    }
  ]
}
```

## ``PUT`` ``/persons/{id}``
### Atualiza uma pessoa pelo ID

Exemplo de requisição
```json
{
  "name": "Ana",
  "document": "59460247954",
  "wage": 5000,
  "gender": "F",
  "addresses": [
    {
      "country": "Brasil",
      "state": "Paraná",
      "city": "SJP",
      "zipCode": 83015717,
      "street": "Rua das flores"
    }
  ]
}
```

Exemplo de resposta
```json
{
  "id": 1,
  "name": "Ana",
  "document": "594.602.479-54",
  "wage": "R$ 5.000,00",
  "gender": "F",
  "addresses": [
    {
      "country": "SJP",
      "state": "Paraná",
      "city": "SJP",
      "zipCode": "83015-717",
      "street": "Rua das flores"
    }
  ]
}
```

## ``DELETE`` ``/persons/{id}``
### Remove uma pessoa pelo ID

# ``/auth``

## ``POST`` ``/auth``
### Realiza a autenticação do usuário, permitindo que ele possa ter acesso as endpoints ``/protected/products`` e ``/protected/demands``

Exemplo de requisição
```json
{
	"username": "gabriel",
	"password": "123456"
}
```

Exemplo de resposta
```json
{
  "token": "eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJNYXJrZXQgQVBJIiwic3ViIjoiMSIsImlhdCI6MTYyNjYzNTEzMCwiZXhwIjoxNjI2NzIxNTMwfQ.BmSrN8dU22JHnMvGm8sZ6GZsv_NWa1OugEPBeXGgpb8",
  "type": "Bearer"
}
```

# ``/protected/products``

## Para o acesso a esse endpoint, é necessário possuir o token JWT realizando o POST em ``/auth`` e passá-lo no cabeçalho Authentication


## ``POST`` ``/protected/products``
### Realiza o cadastro de um produto.

Exemplo de requisição
```json
{
	"description": "Carne",
	"unitPrice": 25
}
```

Exemplo de resposta
```json
{
  "id": 4,
  "description": "Carne",
  "unitPrice": "R$ 25,00"
}
```

## ``GET`` ``/protected/products``
### Retorna uma lista com todos os produtos.

Exemplo de resposta
```json
[
  {
    "id": 1,
    "description": "Arroz",
    "unitPrice": "R$ 15,00"
  },
  {
    "id": 2,
    "description": "Café",
    "unitPrice": "R$ 10,00"
  },
  {
    "id": 3,
    "description": "Feijão",
    "unitPrice": "R$ 12,00"
  },
  {
    "id": 4,
    "description": "Carne",
    "unitPrice": "R$ 25,00"
  }
]
```

#### Esse endpoint possuí vários filtros e ordenações:

- Filtrar por descrição

``/protected/products?description=Arroz``

- Ordenar por todos os parâmetros possíveis,
  para isso deve-se indicar se é ascendente ou descendente.

Os parâmetros possíveis são:
- description
- unitPrice

``/protected/products?sort=description,asc``
``/protected/products?sort=description,desc``
``/protected/products?sort=unitPrice,asc``
``/protected/products?sort=unitPrice,desc``
``/protected/products?sort=description,asc&sort=unitPrice,asc``
``/protected/products?sort=description,desc&sort=unitPrice,desc``

- Também é possível juntar a ordenação com a filtragem.

``/protected/products?description=Arroz&sort=unitPrice,asc``

## ``GET`` ``/protected/products/{id}``
### Retorna um único produto pelo ID

Exemplo de resposta
```json
{
  "id": 1,
  "description": "Arroz",
  "unitPrice": "R$ 15,00"
}
```

## ``PUT`` ``/protected/products/{id}``
### Atualiza um produto pelo ID

Exemplo de requisição
```json
{
  "description": "Carne",
  "unitPrice": 50
}
```

Exemplo de resposta
```json
{
  "id": 4,
  "description": "Carne",
  "unitPrice": "R$ 50,00"
}
```

## ``DELETE`` ``/protected/products/{id}``
### Remove um produto pelo ID

# ``/protected/demands``

## Para o acesso a esse endpoint, é necessário possuir o token JWT realizando o POST em ``/auth`` e passá-lo no cabeçalho Authentication

## ``POST`` ``/protected/demands``
### Realiza o cadastro de um pedido.

Exemplo de requisição
```json
{
  "productIds": [
    1,
    2
  ]
}
```

Exemplo de resposta
```json
{
  "id": 4,
  "total": "R$ 25,00",
  "creationTime": "18/07/2021 16:17",
  "products": [
    {
      "id": 1,
      "description": "Arroz",
      "unitPrice": "R$ 15,00"
    },
    {
      "id": 2,
      "description": "Café",
      "unitPrice": "R$ 10,00"
    }
  ]
}
```

## ``GET`` ``/protected/demands``
### Retorna uma lista com todos os pedidos.

Exemplo de resposta
```json
[
  {
    "id": 1,
    "total": "R$ 37,00",
    "creationTime": "18/07/2021 16:17",
    "products": [
      {
        "id": 1,
        "description": "Arroz",
        "unitPrice": "R$ 15,00"
      },
      {
        "id": 2,
        "description": "Café",
        "unitPrice": "R$ 10,00"
      },
      {
        "id": 3,
        "description": "Feijão",
        "unitPrice": "R$ 12,00"
      }
    ]
  },
  {
    "id": 2,
    "total": "R$ 25,00",
    "creationTime": "18/07/2021 16:17",
    "products": [
      {
        "id": 1,
        "description": "Arroz",
        "unitPrice": "R$ 15,00"
      },
      {
        "id": 2,
        "description": "Café",
        "unitPrice": "R$ 10,00"
      }
    ]
  },
  {
    "id": 3,
    "total": "R$ 15,00",
    "creationTime": "18/07/2021 16:17",
    "products": [
      {
        "id": 1,
        "description": "Arroz",
        "unitPrice": "R$ 15,00"
      }
    ]
  },
  {
    "id": 4,
    "total": "R$ 25,00",
    "creationTime": "18/07/2021 16:17",
    "products": [
      {
        "id": 1,
        "description": "Arroz",
        "unitPrice": "R$ 15,00"
      },
      {
        "id": 2,
        "description": "Café",
        "unitPrice": "R$ 10,00"
      }
    ]
  }
]
```

## ``GET`` ``/protected/demands/{id}``
### Retorna um único pedido pelo ID

Exemplo de resposta
```json
{
  "id": 3,
  "total": "R$ 15,00",
  "creationTime": "18/07/2021 16:18",
  "products": [
    {
      "id": 1,
      "description": "Arroz",
      "unitPrice": "R$ 15,00"
    }
  ]
}
```

## ``PUT`` ``/protected/demands/{id}``
### Atualiza um pedido pelo ID

Exemplo de requisição
```json
{
  "productIds": [
    1,
    2
  ]
}
```

Exemplo de resposta
```json
{
  "id": 4,
  "total": "R$ 25,00",
  "creationTime": "18/07/2021 16:19",
  "products": [
    {
      "id": 1,
      "description": "Arroz",
      "unitPrice": "R$ 15,00"
    },
    {
      "id": 2,
      "description": "Café",
      "unitPrice": "R$ 10,00"
    }
  ]
}
```

## ``DELETE`` ``/protected/products/{id}``
### Remove um produto pelo ID
