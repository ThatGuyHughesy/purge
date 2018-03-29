# Purge

Deletes all GitLab forks for a given user.

## Usage

You will need your GitLab private token which is available here <GITLAB_URL>/profile/personal_access_tokens

```clojure
lein exec src/purge/core.clj <GITLAB_URL> <PRIVATE_TOKEN> <USER_ID>
```

#### Arguments:
<GITLAB_URL> - URL for GitLab server e.g. http://gitlab.example.com or 192.168.1.1

<PRIVATE_TOKEN> - Access token for GitLab API e.g. sfuSJD23nidI2

<USER_ID> - GitLab user to purge e.g. conorhughes or 14

## Development

### Testing

Run tests

```sh
$ Pfffft tests? What could possibly go wrong?!
```

## Copyright & License

Copyright (c) 2017 Conor Hughes - Released under the MIT license.