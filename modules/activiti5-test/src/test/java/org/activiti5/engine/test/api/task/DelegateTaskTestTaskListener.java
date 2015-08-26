package org.activiti5.engine.test.api.task;

import java.util.HashSet;
import java.util.Set;

import org.activiti5.engine.delegate.DelegateTask;
import org.activiti5.engine.delegate.TaskListener;
import org.activiti5.engine.task.IdentityLink;


public class DelegateTaskTestTaskListener implements TaskListener {

  public static final String VARNAME_CANDIDATE_USERS = "candidateUsers";
  public static final String VARNAME_CANDIDATE_GROUPS = "candidateGroups";

  public void notify(DelegateTask delegateTask) {
    Set<IdentityLink> candidates = delegateTask.getCandidates();
    Set<String> candidateUsers = new HashSet<String>();
    Set<String> candidateGroups = new HashSet<String>();
    for (IdentityLink candidate : candidates) {
      if (candidate.getUserId() != null) {
        candidateUsers.add(candidate.getUserId());
      } else if (candidate.getGroupId() != null) {
        candidateGroups.add(candidate.getGroupId());
      }
    }
    delegateTask.setVariable(VARNAME_CANDIDATE_USERS, candidateUsers);
    delegateTask.setVariable(VARNAME_CANDIDATE_GROUPS, candidateGroups);
  }

}