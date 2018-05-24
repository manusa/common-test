#!/usr/bin/env bash
echo "Deploy script running"
if [[ ( "$TRAVIS_BRANCH" = 'master'  ||  "$TRAVIS_BRANCH" = 'develop'  ) && ( "$TRAVIS_PULL_REQUEST" == 'false' ) ]]; then
  echo "Running gradlew uploadArchives"
  openssl aes-256-cbc -K $encrypted_11d0b920ac0b_key -iv $encrypted_11d0b920ac0b_iv -in travis.gpg.enc -out travis.gpg -d
  ./gradlew uploadArchives -PossrhUsername=${SONATYPE_USERNAME} -PossrhPassword=${SONATYPE_PASSWORD} -Psigning.keyId=${GPG_KEY_ID} -Psigning.password=${GPG_KEY_PASSPHRASE} -Psigning.secretKeyRingFile=travis.gpg.enc
else
  echo "Branch not deployable -> exiting"
fi