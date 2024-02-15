package ru.stqa.mantis.manager;

import biz.futureware.mantis.rpc.soap.client.MantisConnectLocator;
import biz.futureware.mantis.rpc.soap.client.MantisConnectPortType;
import biz.futureware.mantis.rpc.soap.client.ObjectRef;
import io.swagger.client.ApiClient;
import io.swagger.client.ApiException;
import io.swagger.client.Configuration;
import io.swagger.client.api.IssuesApi;
import io.swagger.client.api.UserApi;
import io.swagger.client.auth.ApiKeyAuth;
import io.swagger.client.model.Identifier;
import io.swagger.client.model.Issue;
import io.swagger.client.model.User;
import ru.stqa.mantis.model.IssueData;
import ru.stqa.mantis.model.UserData;

import javax.xml.rpc.ServiceException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;

public class SoapApiHelper extends HelperBase{
    MantisConnectPortType mantis;
    public SoapApiHelper(ApplicationManager manager) {
        super(manager);
        try {
            mantis =  new MantisConnectLocator().getMantisConnectPort(
                    new URL(manager.property("soap.endPoint")));
        } catch (ServiceException | MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }




    public void createIssue(IssueData issueData) {

        try {
            var categories = mantis.mc_project_get_categories(manager.property("web.userName"),
                    manager.property("web.password"),
                    BigInteger.valueOf(issueData.project()));
            var issue = new biz.futureware.mantis.rpc.soap.client.IssueData();
            issue.setSummary(issueData.summary());
            issue.setDescription(issueData.description());
            var projectId = new ObjectRef();
            projectId.setId(BigInteger.valueOf(issueData.project()));
            issue.setProject(projectId);
            var categoryId = new Identifier();
            categoryId.setId(issueData.category());
            issue.setCategory(categories[0]);
            mantis.mc_issue_add(manager.property("web.userName"),
                    manager.property("web.password"), issue);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }

    }

}
