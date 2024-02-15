package ru.stqa.mantis.tests;

import org.junit.jupiter.api.Test;
import ru.stqa.mantis.common.CommonFunctions;
import ru.stqa.mantis.model.IssueData;

public class IssueCreationsTests extends TestBase{

    @Test
    void canCreateIssue(){
        app.rest().createIssue(new IssueData()
                .withSummary(CommonFunctions.randomString(10))
                .withDescription(CommonFunctions.randomString(40))
                .withProject(1L));
    }
}
