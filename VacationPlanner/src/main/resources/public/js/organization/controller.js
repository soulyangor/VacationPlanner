'use strict';
 
App.controller('OrganizationController', ['$scope',  'OrganizationService',
    function($scope, OrganizationService) {
          var self = this;
          self.organization={name:'', id: null};
          self.organizations=[];
          self.curOrgName = '';
             
          self.fetchAllOrganizations = function(){
              OrganizationService.fetchAllOrganizations()
                  .then(
                               function(d) {
                                    self.organizations = d;
                               },
                                function(errResponse){
                                    console.error('Error while fetching Organizations');
                                }
                       );
          };
          
          self.fetchAllOrganizations();
            
          self.createOrganization = function(organization){
              OrganizationService.createOrganization(organization)
                      .then(
                      self.fetchAllOrganizations, 
                              function(errResponse){
                                   console.error('Error while creating Organization.');
                              } 
                  );
          };
 
         self.updateOrganization = function(organization){
              OrganizationService.updateOrganization(organization)
                      .then(
                              self.fetchAllOrganizations, 
                              function(errResponse){
                                   console.error('Error while updating Organization.');
                              } 
                  );
          };
 
         self.deleteOrganization = function(orgName){
              OrganizationService.deleteOrganization(orgName)
                      .then(
                              self.fetchAllOrganizations, 
                              function(errResponse){
                                   console.error('Error while deleting Organization.');
                              } 
                  );
          };
 
          self.submit = function() {
              if(self.organization.id===null){
                  console.log('Saving New Organization', self.organization);    
                  self.createOrganization(self.organization);
              }else{
                  self.updateOrganization(self.organization);
                  console.log('Organization updated with name ', self.organization.id);
                  console.log('Organization updated to name ', self.organization.name);
              }
              self.reset();
          };
               
          self.edit = function(orgName){
              console.log('Organization name to be edited', orgName);
              self.organization.id = orgName;
              self.organization.name = orgName;
          };
          
          self.showConfirmDialogThenDelete = function (orgName) {
              self.curOrgName = orgName;
              $('#dialog-confirm').dialog('open');  
          }
               
          self.removeCurOrg = function(){
              console.log('org to be deleted', self.curOrgName);
              if(self.organization.id === self.curOrgName) {//clean form if the organization to be deleted is shown there.
                 self.reset();
              }
              self.deleteOrganization(self.curOrgName);
          };
 
           
          self.reset = function(){
              self.organization={name: '', id: null};
              $scope.myForm.$setPristine(); //reset Form
          };
 
      }]);