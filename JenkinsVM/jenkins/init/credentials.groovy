import com.cloudbees.plugins.credentials.impl.*;
import com.cloudbees.plugins.credentials.*;
import com.cloudbees.plugins.credentials.domains.*;

def usernameGitHub = new File("/run/secrets/github-user").text.trim()
def passwordGitHub = new File("/run/secrets/github-pass").text.trim()
def credentialsIdGitHub = "github-credentials-id"
def descriptionGitHub = "GitHub credentials"

def store = SystemCredentialsProvider.getInstance().getStore()

Credentials gitHubUser = (Credentials) new UsernamePasswordCredentialsImpl(CredentialsScope.GLOBAL,credentialsIdGitHub, descriptionGitHub, usernameGitHub, passwordGitHub)

store.addCredentials(Domain.global(), gitHubUser)