import com.cloudbees.plugins.credentials.impl.*;
import com.cloudbees.jenkins.plugins.sshcredentials.impl.*;
import com.cloudbees.plugins.credentials.*;
import com.cloudbees.plugins.credentials.domains.*;

def store = SystemCredentialsProvider.getInstance().getStore()

def usernameGitHub = new File("/run/secrets/github-user").text.trim()
def passwordGitHub = new File("/run/secrets/github-pass").text.trim()
def credentialsIdGitHub = "github-credentials-id"
def descriptionGitHub = "GitHub credentials"
def privateKeyUser = "cragus"
def privateKeyId = "spigkey"

Credentials gitHubUser = (Credentials) new UsernamePasswordCredentialsImpl(
	CredentialsScope.GLOBAL, 
	credentialsIdGitHub, 
	descriptionGitHub, 
	usernameGitHub, 
	passwordGitHub)

Credentials privateKey = new BasicSSHUserPrivateKey(
	CredentialsScope.GLOBAL,
	privateKeyId, 
	privateKeyUser,
	new BasicSSHUserPrivateKey.FileOnMasterPrivateKeySource("/home/keyfile"),
	"",
	"remote host key")

store.addCredentials(Domain.global(), gitHubUser)
store.addCredentials(Domain.global(), privateKey)