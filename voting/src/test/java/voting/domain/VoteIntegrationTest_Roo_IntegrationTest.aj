// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package voting.domain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import voting.domain.VoteDataOnDemand;

privileged aspect VoteIntegrationTest_Roo_IntegrationTest {
    
    declare @type: VoteIntegrationTest: @RunWith(SpringJUnit4ClassRunner.class);
    
    declare @type: VoteIntegrationTest: @ContextConfiguration(locations = "classpath:/META-INF/spring/applicationContext.xml");
    
    declare @type: VoteIntegrationTest: @Transactional;
    
    @Autowired
    private VoteDataOnDemand VoteIntegrationTest.dod;
    
    @Test
    public void VoteIntegrationTest.testCountVotes() {
        org.junit.Assert.assertNotNull("Data on demand for 'Vote' failed to initialize correctly", dod.getRandomVote());
        long count = voting.domain.Vote.countVotes();
        org.junit.Assert.assertTrue("Counter for 'Vote' incorrectly reported there were no entries", count > 0);
    }
    
    @Test
    public void VoteIntegrationTest.testFindVote() {
        voting.domain.Vote obj = dod.getRandomVote();
        org.junit.Assert.assertNotNull("Data on demand for 'Vote' failed to initialize correctly", obj);
        java.lang.Long id = obj.getId();
        org.junit.Assert.assertNotNull("Data on demand for 'Vote' failed to provide an identifier", id);
        obj = voting.domain.Vote.findVote(id);
        org.junit.Assert.assertNotNull("Find method for 'Vote' illegally returned null for id '" + id + "'", obj);
        org.junit.Assert.assertEquals("Find method for 'Vote' returned the incorrect identifier", id, obj.getId());
    }
    
    @Test
    public void VoteIntegrationTest.testFindAllVotes() {
        org.junit.Assert.assertNotNull("Data on demand for 'Vote' failed to initialize correctly", dod.getRandomVote());
        long count = voting.domain.Vote.countVotes();
        org.junit.Assert.assertTrue("Too expensive to perform a find all test for 'Vote', as there are " + count + " entries; set the findAllMaximum to exceed this value or set findAll=false on the integration test annotation to disable the test", count < 250);
        java.util.List<voting.domain.Vote> result = voting.domain.Vote.findAllVotes();
        org.junit.Assert.assertNotNull("Find all method for 'Vote' illegally returned null", result);
        org.junit.Assert.assertTrue("Find all method for 'Vote' failed to return any data", result.size() > 0);
    }
    
    @Test
    public void VoteIntegrationTest.testFindVoteEntries() {
        org.junit.Assert.assertNotNull("Data on demand for 'Vote' failed to initialize correctly", dod.getRandomVote());
        long count = voting.domain.Vote.countVotes();
        if (count > 20) count = 20;
        java.util.List<voting.domain.Vote> result = voting.domain.Vote.findVoteEntries(0, (int) count);
        org.junit.Assert.assertNotNull("Find entries method for 'Vote' illegally returned null", result);
        org.junit.Assert.assertEquals("Find entries method for 'Vote' returned an incorrect number of entries", count, result.size());
    }
    
    @Test
    public void VoteIntegrationTest.testFlush() {
        voting.domain.Vote obj = dod.getRandomVote();
        org.junit.Assert.assertNotNull("Data on demand for 'Vote' failed to initialize correctly", obj);
        java.lang.Long id = obj.getId();
        org.junit.Assert.assertNotNull("Data on demand for 'Vote' failed to provide an identifier", id);
        obj = voting.domain.Vote.findVote(id);
        org.junit.Assert.assertNotNull("Find method for 'Vote' illegally returned null for id '" + id + "'", obj);
        boolean modified =  dod.modifyVote(obj);
        java.lang.Integer currentVersion = obj.getVersion();
        obj.flush();
        org.junit.Assert.assertTrue("Version for 'Vote' failed to increment on flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }
    
    @Test
    public void VoteIntegrationTest.testMerge() {
        voting.domain.Vote obj = dod.getRandomVote();
        org.junit.Assert.assertNotNull("Data on demand for 'Vote' failed to initialize correctly", obj);
        java.lang.Long id = obj.getId();
        org.junit.Assert.assertNotNull("Data on demand for 'Vote' failed to provide an identifier", id);
        obj = voting.domain.Vote.findVote(id);
        boolean modified =  dod.modifyVote(obj);
        java.lang.Integer currentVersion = obj.getVersion();
        voting.domain.Vote merged = (voting.domain.Vote) obj.merge();
        obj.flush();
        org.junit.Assert.assertEquals("Identifier of merged object not the same as identifier of original object", merged.getId(), id);
        org.junit.Assert.assertTrue("Version for 'Vote' failed to increment on merge and flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }
    
    @Test
    public void VoteIntegrationTest.testPersist() {
        org.junit.Assert.assertNotNull("Data on demand for 'Vote' failed to initialize correctly", dod.getRandomVote());
        voting.domain.Vote obj = dod.getNewTransientVote(Integer.MAX_VALUE);
        org.junit.Assert.assertNotNull("Data on demand for 'Vote' failed to provide a new transient entity", obj);
        org.junit.Assert.assertNull("Expected 'Vote' identifier to be null", obj.getId());
        obj.persist();
        obj.flush();
        org.junit.Assert.assertNotNull("Expected 'Vote' identifier to no longer be null", obj.getId());
    }
    
    @Test
    public void VoteIntegrationTest.testRemove() {
        voting.domain.Vote obj = dod.getRandomVote();
        org.junit.Assert.assertNotNull("Data on demand for 'Vote' failed to initialize correctly", obj);
        java.lang.Long id = obj.getId();
        org.junit.Assert.assertNotNull("Data on demand for 'Vote' failed to provide an identifier", id);
        obj = voting.domain.Vote.findVote(id);
        obj.remove();
        obj.flush();
        org.junit.Assert.assertNull("Failed to remove 'Vote' with identifier '" + id + "'", voting.domain.Vote.findVote(id));
    }
    
}
