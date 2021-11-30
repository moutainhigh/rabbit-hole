#!/usr/bin/env bash
# 手动推包
ID=sonatype
URL="https://oss.sonatype.org/content/repositories/snapshots"
REPO="${ID}::default::${URL}"
#mvn install --batch-mode -Dmaven.test.skip=true
#mvn --batch-mode deploy -Dmaven.test.skip=true -DaltReleaseDeploymentRepository="${REPO}" -DaltSnapshotDeploymentRepository="${REPO}"
mvn --batch-mode deploy -pl . -Dmaven.test.skip=true -DaltReleaseDeploymentRepository="${REPO}" -DaltSnapshotDeploymentRepository="${REPO}"
mvn --batch-mode deploy -pl rabbit-basic -Dmaven.test.skip=true -DaltReleaseDeploymentRepository="${REPO}" -DaltSnapshotDeploymentRepository="${REPO}"
mvn --batch-mode deploy -pl rabbit-basic/rabbit-common -Dmaven.test.skip=true -DaltReleaseDeploymentRepository="${REPO}" -DaltSnapshotDeploymentRepository="${REPO}"
mvn --batch-mode deploy -pl rabbit-basic/rabbit-usercontext -Dmaven.test.skip=true -DaltReleaseDeploymentRepository="${REPO}" -DaltSnapshotDeploymentRepository="${REPO}"
mvn --batch-mode deploy -pl rabbit-basic/rabbit-usercontext/rabbit-usercontext-autoconfigure -Dmaven.test.skip=true -DaltReleaseDeploymentRepository="${REPO}" -DaltSnapshotDeploymentRepository="${REPO}"
mvn --batch-mode deploy -pl rabbit-basic/rabbit-usercontext/rabbit-usercontext-basic -Dmaven.test.skip=true -DaltReleaseDeploymentRepository="${REPO}" -DaltSnapshotDeploymentRepository="${REPO}"
mvn --batch-mode deploy -pl rabbit-basic/rabbit-usercontext/rabbit-usercontext-interface -Dmaven.test.skip=true -DaltReleaseDeploymentRepository="${REPO}" -DaltSnapshotDeploymentRepository="${REPO}"
mvn --batch-mode deploy -pl rabbit-services -Dmaven.test.skip=true -DaltReleaseDeploymentRepository="${REPO}" -DaltSnapshotDeploymentRepository="${REPO}"
mvn --batch-mode deploy -pl rabbit-services/rabbit-service-chaos -Dmaven.test.skip=true -DaltReleaseDeploymentRepository="${REPO}" -DaltSnapshotDeploymentRepository="${REPO}"
mvn --batch-mode deploy -pl rabbit-services/rabbit-service-chaos/rabbit-service-chaos-api -Dmaven.test.skip=true -DaltReleaseDeploymentRepository="${REPO}" -DaltSnapshotDeploymentRepository="${REPO}"

