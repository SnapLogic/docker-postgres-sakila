NAME_POSTGRES = snapdevteam/postgres-sakila
NAME_OPENLDAP = snapdevteam/openldap-sakila
VERSION = 1.0

.PHONY: build build-nocache test tag-latest push push-latest release git-tag-version

build:
    docker build -t $(NAME_POSTGRES):$(VERSION) --rm image/postgresql
    docker build -t $(NAME_OPENLDAP):$(VERSION) --rm image/openldap

build-nocache:
    docker build -t $(NAME_POSTGRES):$(VERSION) --no-cache --rm image/postgresql
    docker build -t $(NAME_OPENLDAP):$(VERSION) --no-cache --rm image/openldap

test:
    # env NAME=$(NAME_POSTGRES) VERSION=$(VERSION) bats test/test.bats
    # env NAME=$(NAME_OPENLDAP) VERSION=$(VERSION) bats test/test.bats

tag-latest:
    # docker tag $(NAME_POSTGRES):$(VERSION) $(NAME_POSTGRES):latest
    # docker tag $(NAME_OPENLDAP):$(VERSION) $(NAME_OPENLDAP):latest

push:
    docker push $(NAME_POSTGRES):$(VERSION)
    docker push $(NAME_OPENLDAP):$(VERSION)

push-latest:
    docker push $(NAME_POSTGRES):latest
    docker push $(NAME_OPENLDAP):latest

release: build test tag-latest push push-latest

git-tag-version: release
    #git tag -a v$(VERSION) -m "v$(VERSION)"
    #git push origin v$(VERSION)
