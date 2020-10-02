pragma solidity ^0.4.25;
//pragma experimental ABIEncoderV2;

contract Election{
    
    struct Candidate{
        string name;
        uint voteCount;
    }
    
    struct Voter{
        bool autherized;
        bool voted;
        uint vote;
    }
    
    address public owner;
    string public electionName;
    
    mapping (address => Voter) public voters;
    Candidate[] public candidates;
    uint public totalVotes;
    uint public candidateConut;
     
    modifier ownerOnly() {
        require(msg.sender == owner);
        _;
    }
    
    constructor(string _name) public {
        candidateConut=0;
        owner=msg.sender;
        electionName= _name;
    }
    
    function addcandidate(string _name) ownerOnly public {
        candidates.push(Candidate(_name,0));
        candidateConut++;
    }
    
    function getNumCandidate() public view returns (uint) {
        return candidates.length;
    }
    
      mapping(uint => Candidate[]) eCandidate;
   /*
    function getNumCandidates() public view returns (Candidate[] memory) {
        
         Candidate[]    memory id = new Candidate[](candidateConut);
      for (uint i = 0; i < candidateConut; i++) {
          Candidate storage candidate = candidates[i];
          id[i] = candidate;
      }
      return id;
    }
    */
    
    
    function authorize (address _person) ownerOnly public{
        voters[_person].autherized=true;
    }
    
    function vote (uint _voteIndex) public {
        require(!voters[msg.sender].voted);
        require(voters[msg.sender].autherized);
        
        voters[msg.sender].vote = _voteIndex;
        voters[msg.sender].voted = true;
        
        candidates[_voteIndex].voteCount+=1;
        totalVotes +=1;
    }
    
    function end () ownerOnly public {
        selfdestruct(owner);
    }
    
}
