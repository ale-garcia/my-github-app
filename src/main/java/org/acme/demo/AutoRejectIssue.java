package org.acme.demo;

import java.io.IOException;
import java.util.Locale;

import org.kohsuke.github.GHEventPayload;

import io.quarkiverse.githubapp.event.Issue;

public class AutoRejectIssue {
    void onIssueOpened(@Issue.Opened GHEventPayload.Issue payload) throws IOException {
        var issue = payload.getIssue();
        var keyword = "osgi";
            if (issue.getTitle().toLowerCase(Locale.ROOT).contains(keyword)
                    || issue.getBody() != null && issue.getBody().toLowerCase(Locale.ROOT).contains(keyword)) {
                payload.getIssue().comment("Sorry, " + keyword + " integration is not supported at the moment.");
                payload.getIssue().close();
            }
    }
}