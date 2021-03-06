import com.buildit.jenkins.configfetcher.ConfigFetcher
import com.buildit.artifactfetcher.ArtifactFetcher

def jenkinsConfig = new ConfigFetcher().fetch()

def version = jenkinsConfig?.jenkins?.version

def artifactPattern = version?.artifactPattern
def artifact = version?.artifact

if(!artifactPattern || !artifact){
    throw new IllegalArgumentException("Unable to download jenkins file. Config version dscovered was ${version}")
}

def artifactArray = new ArtifactFetcher().fetch(artifactPattern, [artifact])
File warFile = artifactArray[0]

if(!warFile || !warFile.exists()){
    throw new IllegalArgumentException("Unable to download jenkins file. Config version dscovered was ${version}")
}

println(warFile.absolutePath)
return warFile.absolutePath