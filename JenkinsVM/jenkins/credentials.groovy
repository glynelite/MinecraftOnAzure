import com.cloudbees.plugins.credentials.impl.*;
import com.cloudbees.jenkins.plugins.sshcredentials.impl.*;
import com.cloudbees.plugins.credentials.*;
import com.cloudbees.plugins.credentials.domains.*;

def store = SystemCredentialsProvider.getInstance().getStore()

def usernameGitHub = new File("/run/secrets/github-user").text.trim()
def passwordGitHub = new File("/run/secrets/github-pass").text.trim()
def remoteHostUser = new File("/run/secrets/remote-host-user").text.trim()
def remoteHostKeyId = "spigkey"
def gitHubCredentialsId = "github-credentials-id"

Credentials gitHubUser = (Credentials) new UsernamePasswordCredentialsImpl(
	CredentialsScope.GLOBAL, 
	gitHubCredentialsId,
	"gitHub user credentials",
	usernameGitHub,
	passwordGitHub)

Credentials privateKey = new BasicSSHUserPrivateKey(
	CredentialsScope.GLOBAL,
	remoteHostKeyId,
	remoteHostUser,
	new BasicSSHUserPrivateKey.FileOnMasterPrivateKeySource("/home/keyfile"),
	"",
	"remote host private key")

store.addCredentials(Domain.global(), gitHubUser)
store.addCredentials(Domain.global(), privateKey)