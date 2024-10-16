package com.csit321.ctfbackend.user.utility;

import com.csit321.ctfbackend.user.model.Team;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;

@Component
public class TeamRankingUtility {

    /**
     * Ranks teams based on their scores.
     * Teams with higher scores are ranked higher.
     * Teams with equal scores receive the same rank.
     *
     * @param teams List of teams to rank.
     */
    public static void rankTeams(List<Team> teams) {
        teams.sort(Comparator.comparingDouble(Team::getScore).reversed());

        int rank = 1;
        int position = 1;
        double previousScore = Double.NaN;

        for (Team team : teams) {
            if (Double.isNaN(previousScore) || Double.compare(team.getScore(), previousScore) != 0) {
                rank = position;
            }
            team.setRank(rank);
            previousScore = team.getScore();
            position++;
        }
    }
}
