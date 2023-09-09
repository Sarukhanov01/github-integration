# github-integration
Atipera-Github-API


## Features

- Fetch GitHub repositories for a given user.
- Retrieve information about branches in each repository.
- Display the last commit SHA for each branch.
- Exclude forked repositories.


## Usage
To use the GitHub Integration Service, you can make HTTP GET requests to the provided endpoints to fetch GitHub repositories for a specific user.

Endpoints
List GitHub Repositories
URL: /api/github/repositories/{username}

Method: GET

Produces: JSON (application/json)

Description: Fetch GitHub repositories for a specific user.

### Parameters:

`-:username (required): The GitHub username of the user.`

### Headers:

`-Accept: application/json (required): The desired response format.`

Example Request:
`GET http://localhost:8080/api/github/repositories/{username}`

## Example Response:

```json
[
    {
        "name": "AlertWebService",
        "ownerLogin": "Sarukhanov01",
        "branches": [
            {
                "name": "master",
                "lastCommitSha": "d8c4579d3a10ddd426602d340af725d45c877699"
            }
        ]
    },
    {
        "name": "github-integration",
        "ownerLogin": "Sarukhanov01",
        "branches": [
            {
                "name": "main",
                "lastCommitSha": "77a96287d71cdbc8253e03d2fd565523c380d438"
            },
            {
                "name": "master",
                "lastCommitSha": "9a63ce81ba49010b3800e3b021c412cad84f6fc8"
            }
        ]
    },
    {
        "name": "KibritTasks",
        "ownerLogin": "Sarukhanov01",
        "branches": [
            {
                "name": "master",
                "lastCommitSha": "ee90706a1f4bc4ff6a0eabe99b0ee9639221dc3f"
            }
        ]
    }
]

```
