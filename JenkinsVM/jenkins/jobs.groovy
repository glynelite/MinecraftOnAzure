import hudson.plugins.git.*;
import jenkins.model.*;
import org.jenkinsci.plugins.workflow.cps.*;
import org.jenkinsci.plugins.workflow.job.*;
import com.cloudbees.plugins.credentials.*;
import com.cloudbees.plugins.credentials.common.*;
import com.cloudbees.plugins.credentials.CredentialsProvider;

def instance = Jenkins.getInstance()
existingJobs = instance.getAllItems()

def pluginsRepoUrl = System.env.GITHUB_PLUGINS_REPO_URL
def configRepoUrl = System.env.GITHUB_CONFIG_REPO_URL

coreJobs = [ new Expando(
				Jobname: "plugin-pipe",
				RepoUrl: pluginsRepoUrl,
				CredentialsId: "github-credentials-id",
				Jenkinsfile: "jenkins/Jenkinsfile",
				BranchFilter: "*/master"),
			new Expando(
				Jobname: "config-pipe",
				RepoUrl: configRepoUrl,
				CredentialsId: "github-credentials-id",
				Jenkinsfile: "jenkins/Jenkinsfile",
				BranchFilter: "*/master"),]
			
for(coreJob in coreJobs){
		
	def shouldCreate = true
	
	existingJobs.each { j ->  
        if (j.fullName.contains(coreJob.Jobname)) {
        	println '--------------------> Job already exists: ' + coreJob.Jobname
        	shouldCreate = false
		}
	}
	
	if (!shouldCreate) {
        continue
    }
	
	println '--------------------> Creating job: ' + coreJob.Jobname
	
	def gitSCM = new GitSCM([new UserRemoteConfig(coreJob.RepoUrl, null, null, coreJob.CredentialsId)], 
	[new BranchSpec(coreJob.BranchFilter)], 
	false, null, null, null, null);

	workflowJob = new WorkflowJob(instance, coreJob.Jobname);
	workflowJob.definition = new CpsScmFlowDefinition(gitSCM, coreJob.Jenkinsfile);
					
	instance.add(workflowJob, workflowJob.name);
	
	println '--------------------> Job created: ' + coreJob.Jobname            
}