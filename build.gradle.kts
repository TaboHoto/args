//gradle publishToMavenLocal
defaultTasks("jar")
plugins{
 id("java-library")
 id("maven-publish")
}
group = "tabou"
version = "0.1"
sourceSets{
 main{
  java.srcDir("src")
 }
}
tasks.withType<JavaCompile>().configureEach{
  options.encoding = "UTF-8"
}
tasks.withType<Jar>().configureEach{
 archiveBaseName.set(project.group.toString() + "."
  + rootProject.name)
}
publishing {
 publications {
  create<MavenPublication>("maven"){
   from(components["java"])
   artifactId = (project.group.toString() + "."
    + rootProject.name)
  }
 }
}
