import hudson.plugins.git.*;
import jenkins.model.*;
import org.jenkinsci.plugins.workflow.cps.*;
import org.jenkinsci.plugins.workflow.job.*;
import com.cloudbees.plugins.credentials.*;
import com.cloudbees.plugins.credentials.CredentialsProvider;

def instance = Jenkins.getInstance()

def gitSCM = new GitSCM(
	[new UserRemoteConfig("https://github.com/glynelite/MobCap", null, null, "github-credentials-id")],
	[new BranchSpec('*/master')], 
	false, 
	null, 
	null, 
	null, 
	null);

workflowJob = new WorkflowJob(instance, "workflow");
workflowJob.definition = new CpsScmFlowDefinition(gitSCM, "jenkins/Jenkinsfile");
			
instance.add(workflowJob, workflowJob.name);