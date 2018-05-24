#!/usr/bin/env bash
echo "Deploy script running"
if [[ ( "$TRAVIS_BRANCH" = 'master'  ||  "$TRAVIS_BRANCH" = 'develop'  ) && ( "$TRAVIS_PULL_REQUEST" == 'false' ) ]]; then
  echo "Running gradlew uploadArchives"
  ./gradlew uploadArchives -PossrhUsername=${SONATYPE_USERNAME} -PossrhPassword=${SONATYPE_PASSWORD} -Psigning.keyId=${GPG_KEY_ID} -Psigning.password=${GPG_KEY_PASSPHRASE} -Psigning.secretKeyRingFile=my.travis.gpg
else
  echo "Branch not deployable -> exiting"
fi