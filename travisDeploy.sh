#!/bin/bash
####################################################################################################
#  Script     : k8s
#  Author     : Marc Nuri
#  Date       : 2018/05/24
#  Last Edited: 2018/05/25, Marc Nuri
#  Description: Script to automate deployment of artifacts to Sonatype ossrh
####################################################################################################

echo "Deployment script running"
if [[ ( "$TRAVIS_BRANCH" = 'master'  ||  "$TRAVIS_BRANCH" = 'develop'  ) && ( "$TRAVIS_PULL_REQUEST" == 'false' ) ]]; then
  echo "Running gradlew uploadArchives"
  openssl aes-256-cbc -K $encrypted_11d0b920ac0b_key -iv $encrypted_11d0b920ac0b_iv -in travis.gpg.enc -out travis.gpg -d
  ./gradlew uploadArchives -PossrhUsername=${SONATYPE_USERNAME} -PossrhPassword=${SONATYPE_PASSWORD} -Psigning.keyId=${GPG_KEY_ID} -Psigning.password=${GPG_KEY_PASSPHRASE} -Psigning.secretKeyRingFile=travis.gpg || exit 1
else
  echo "Branch not deployable -> exiting"
fi